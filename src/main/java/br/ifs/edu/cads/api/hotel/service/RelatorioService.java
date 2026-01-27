package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.rest.dto.CancelamentoComMultaDto;
import br.ifs.edu.cads.api.hotel.rest.dto.QuartoReservaDto;
import br.ifs.edu.cads.api.hotel.rest.dto.QuartoOcupacaoDto;
import br.ifs.edu.cads.api.hotel.rest.dto.ReservaSimplesDto;
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
import java.time.LocalTime;
import java.util.List;

@Service
public class RelatorioService {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;
    private final QuartoRepository quartoRepository;
    private final CancelamentoRepository cancelamentoRepository;

    public RelatorioService(ReservaRepository reservaRepository, ReservaMapper reservaMapper, QuartoRepository quartoRepository, CancelamentoRepository cancelamentoRepository) {
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
        this.quartoRepository = quartoRepository;
        this.cancelamentoRepository = cancelamentoRepository;
    }

    public List<ReservaSimplesDto> gerarRelatorioReservasPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
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
}
