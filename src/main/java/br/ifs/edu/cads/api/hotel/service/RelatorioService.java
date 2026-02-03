package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.enums.FormaPagamento;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
}
