package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.entity.Cancelamento;
import br.ifs.edu.cads.api.hotel.entity.Hospede;
import br.ifs.edu.cads.api.hotel.enums.FormaPagamento;
import br.ifs.edu.cads.api.hotel.enums.StatusReserva;
import br.ifs.edu.cads.api.hotel.exception.ResourceNotFoundException;
import br.ifs.edu.cads.api.hotel.repository.HospedeRepository;
import br.ifs.edu.cads.api.hotel.rest.dto.*;
import br.ifs.edu.cads.api.hotel.rest.dto.mapper.HospedeMapper;
import br.ifs.edu.cads.api.hotel.rest.dto.mapper.ReservaMapper;
import br.ifs.edu.cads.api.hotel.entity.Quarto;
import br.ifs.edu.cads.api.hotel.entity.Reserva;
import br.ifs.edu.cads.api.hotel.enums.StatusRelatorioOcupacao;
import br.ifs.edu.cads.api.hotel.repository.CancelamentoRepository;
import br.ifs.edu.cads.api.hotel.repository.QuartoRepository;
import br.ifs.edu.cads.api.hotel.repository.ReservaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class RelatorioService {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;
    private final QuartoRepository quartoRepository;
    private final CancelamentoRepository cancelamentoRepository;
    private final HospedeRepository hospedeRepository;
    private final HospedeMapper hospedeMapper;

    public RelatorioService(ReservaRepository reservaRepository, ReservaMapper reservaMapper, QuartoRepository quartoRepository, CancelamentoRepository cancelamentoRepository, HospedeRepository hospedeRepository, HospedeMapper hospedeMapper) {
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
        this.quartoRepository = quartoRepository;
        this.cancelamentoRepository = cancelamentoRepository;
        this.hospedeRepository = hospedeRepository;
        this.hospedeMapper = hospedeMapper;
    }

    public List<ReservaSimplesDto> gerarRelatorioReservasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        List<Reserva> reservas = reservaRepository.findReservaByDataInicioBetween(dataInicial, dataFinal);

        return reservas.stream().map(reservaMapper::toDtoSimples).toList();
    }

    public Page<QuartoOcupacaoDto> gerarRelatorioOcupacao(Integer idCategoriaQuarto, StatusRelatorioOcupacao statusFiltro, Pageable pageable) {
        Page<QuartoReservaDto> paginaBruta = quartoRepository.findParaRelatorioOcupacao(idCategoriaQuarto, statusFiltro.name(), pageable);

        Page<QuartoOcupacaoDto> paginaPronta = paginaBruta.map(bruto -> {
            Quarto quarto = bruto.quarto();
            Reserva reserva = bruto.reserva();

            String statusFinal;
            if (reserva != null &&
                (statusFiltro == StatusRelatorioOcupacao.CHECKIN || statusFiltro == StatusRelatorioOcupacao.CHECKOUT)) {
                statusFinal = reserva.getStatusReserva().name();
            } else {
                statusFinal = quarto.getStatusQuarto().name();
            }

            return new QuartoOcupacaoDto(
                    quarto.getNumQuarto(),
                    "Bloco " + quarto.getNumBloco() + " - Andar " + quarto.getNumAndar(),
                    quarto.getCategoria().getNome(),
                    statusFinal
            );
        });

        return paginaPronta;
    }

    public Page<CancelamentoComMultaDto> gerarRelatorioMultas(LocalDate inicio, LocalDate fim, Pageable pageable) {

        return cancelamentoRepository.findByDataCancelamentoBetweenAndValorMultaGreaterThan(
                inicio.atStartOfDay(),
                fim.atTime(LocalTime.MAX),
                BigDecimal.ZERO,
                pageable
        ).map(c -> new CancelamentoComMultaDto(
                c.getReserva().getHospede().getNome(),
                c.getReserva().getCategoriaQuarto().getNome(),
                c.getDataCancelamento(),
                c.getValorMulta()
        ));
    }

    public List<HospedeUsuarioRelatorioDto> listarHospedesAtivos() {
        return hospedeRepository.findByUsuarioAtivoTrue().stream().map(hospedeMapper::toRelatorioDto).toList();
    }

    public Page<ReservaPagamentoRelatorioDto> gerarRelatorioPagamento(FormaPagamento forma, LocalDateTime dataInicio,LocalDateTime dataFim, Pageable pageable) {
        return reservaRepository.relatorioPorFormaPagamento(forma, dataInicio, dataFim, pageable);
    }

    public FaturamentoDTO calcularFaturamento(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<Reserva> reservasAtivas = reservaRepository.findReservasAtivas(dataInicio, dataFim);
        List<Cancelamento> cancelamentos = cancelamentoRepository.findByDataCancelamentoBetween(dataInicio, dataFim);

        BigDecimal totalBrutoDiarias = BigDecimal.ZERO;
        BigDecimal totalDescontos = BigDecimal.ZERO;

        for (Reserva r : reservasAtivas) {
            long totalNoites = ChronoUnit.DAYS.between(r.getDataInicio().toLocalDate(), r.getDataFim().toLocalDate());

            BigDecimal valorDiariaComum = r.getValorReserva().divide(BigDecimal.valueOf(totalNoites), 2, RoundingMode.HALF_UP);

            LocalDate dataAtual = r.getDataInicio().toLocalDate();
            while (dataAtual.isBefore(r.getDataFim().toLocalDate())) {

                totalBrutoDiarias = totalBrutoDiarias.add(valorDiariaComum);

                DayOfWeek dia = dataAtual.getDayOfWeek();

                if (dia != DayOfWeek.FRIDAY && dia != DayOfWeek.SATURDAY && dia != DayOfWeek.SUNDAY) {
                    BigDecimal descontoNoite = valorDiariaComum.multiply(new BigDecimal("0.20"));
                    totalDescontos = totalDescontos.add(descontoNoite);
                }

                dataAtual = dataAtual.plusDays(1);
            }
        }

        BigDecimal totalMultas = cancelamentos.stream()
                .map(Cancelamento::getValorMulta)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal brutoFinal = totalBrutoDiarias.add(totalMultas);
        BigDecimal liquidoFinal = brutoFinal.subtract(totalDescontos);

        return new FaturamentoDTO(brutoFinal, totalDescontos, liquidoFinal);
    }

    @Transactional(readOnly = true)
    public Page<ReservaRelatorioHospedeDto> gerarHistoricoHospede(Long idHospede, Pageable pageable) {
        Hospede hospede = hospedeRepository.findById(idHospede).orElseThrow(
                () -> new ResourceNotFoundException("Hospede de ID " + idHospede + " não encontrado.")
        );

        if (hospede.getUsuario() == null) {
            throw new ResourceNotFoundException("Hospede de ID " + idHospede + " não possui reservas como titular.");
        }

        Page<Reserva> reservasHospede = reservaRepository.findByHospede(hospede, pageable);

        Page<ReservaRelatorioHospedeDto> reservasHospedeDto = reservasHospede.map(r -> {
                BigDecimal valorPago = r.getValorReserva();

                if (r.getStatusReserva().equals(StatusReserva.CANCELADO)) {

                    Optional<Cancelamento> cancelamento = cancelamentoRepository.findByReserva(r);

                    if (cancelamento.isEmpty()) {
                        // nao é o esperado, decidido por setar como zero inves de jogar um erro
                        valorPago = BigDecimal.ZERO;
                    } else {
                        valorPago = cancelamento.get().getValorMulta();
                    }
                }
                return new ReservaRelatorioHospedeDto(
                        r.getDataInicio(),
                        r.getDataFim(),
                        r.getDataCheckIn(),
                        r.getDataCheckOut(),
                        r.getCategoriaQuarto().getNome(),
                        r.getStatusReserva(),
                        valorPago
                );
        });

        return reservasHospedeDto;
    }

    @Transactional(readOnly = true)
    public List<RelatorioUtilizacaoCategoriaDto> gerarRelatorioUtilizacaoCategoria(LocalDateTime dataInicio, LocalDateTime dataFim) {

        List<RelatorioUtilizacaoCategoriaDto> relatorioUtilizacaoCategoriaDtos = reservaRepository.relatorioUtilizacaoCategoria(dataInicio,dataFim);

        System.out.println("PRINTTTTTT");
        System.out.println(quartoRepository.countQuartosByCategoriaNome("Presidencial"));
        System.out.println("MAAASTEEERR");
        System.out.println(quartoRepository.countQuartosByCategoriaNome("Master"));
        return relatorioUtilizacaoCategoriaDtos.stream().map(cat -> new RelatorioUtilizacaoCategoriaDto(
                cat.categoria(),
                cat.totalReservas(),
                String.format("%.2f%%", (cat.totalReservas()/ (double)  quartoRepository.countQuartosByCategoriaNome(cat.categoria())) *100.0)))
                .toList();
    }
}
