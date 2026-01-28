package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.exception.BusinessRuleException;
import br.ifs.edu.cads.api.hotel.rest.dto.*;
import br.ifs.edu.cads.api.hotel.rest.dto.mapper.CancelamentoMapper;
import br.ifs.edu.cads.api.hotel.rest.dto.mapper.ReservaMapper;
import br.ifs.edu.cads.api.hotel.entity.*;
import br.ifs.edu.cads.api.hotel.enums.StatusReserva;
import br.ifs.edu.cads.api.hotel.exception.ResourceNotFoundException;
import br.ifs.edu.cads.api.hotel.repository.CancelamentoRepository;
import br.ifs.edu.cads.api.hotel.repository.ReservaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final QuartoService quartoService;
    private final ReservaMapper reservaMapper;
    private final CategoriaQuartoService categoriaQuartoService;
    private final HospedeService hospedeService;
    private final FuncionarioService funcionarioService;
    private final CancelamentoMapper cancelamentoMapper;
    private final CancelamentoRepository cancelamentoRepository;

    public ReservaService(ReservaRepository reservaRepository, QuartoService quartoService, ReservaMapper reservaMapper, CategoriaQuartoService categoriaQuartoService, HospedeService hospedeService, FuncionarioService funcionarioService, CancelamentoMapper cancelamentoMapper, CancelamentoRepository cancelamentoRepository) {
        this.reservaRepository = reservaRepository;
        this.quartoService = quartoService;
        this.reservaMapper = reservaMapper;
        this.categoriaQuartoService = categoriaQuartoService;
        this.hospedeService = hospedeService;
        this.funcionarioService = funcionarioService;
        this.cancelamentoMapper = cancelamentoMapper;
        this.cancelamentoRepository = cancelamentoRepository;
    }

    public ReservaDto criarReserva(ReservaFormDto reservaFormDto) {
        DisponibilidadeReservaDto disponibilidadeReservaDto = checarDisponibilidade(
                new ReservaBuscaDto(
                        reservaFormDto.dataInicio(),
                        reservaFormDto.dataFim(),
                        reservaFormDto.idCategoriaQuarto())
        );

        if (!disponibilidadeReservaDto.estaDisponivel()) {
            throw new BusinessRuleException("Quarto não disponível para o período selecionado.");
        }

        CategoriaQuarto categoriaQuarto = categoriaQuartoService.findById(reservaFormDto.idCategoriaQuarto());
        Hospede hospede = hospedeService.findById(reservaFormDto.idHospede());
        Funcionario funcionario = funcionarioService.findFuncionarioById(reservaFormDto.idFuncionario());

        Reserva reserva = reservaMapper.formToEntity(reservaFormDto);
        reserva.setCategoriaQuarto(categoriaQuarto);
        reserva.setHospede(hospede);
        reserva.setFuncionario(funcionario);
        reserva.setValorReserva(disponibilidadeReservaDto.valorReserva());

        reserva = reservaRepository.save(reserva);
        return reservaMapper.toDto(reserva);
    }

    public DisponibilidadeReservaDto checarDisponibilidade(ReservaBuscaDto reservaBuscaDto) {
        Integer reservasOcupadas = reservaRepository.contarReservasConflitantes(
                reservaBuscaDto.dataInicio(),
                reservaBuscaDto.dataFim(),
                reservaBuscaDto.categoriaId()
        );

        Integer totalQuartos = quartoService.countTotalPorCategoria(reservaBuscaDto.categoriaId());

        if (totalQuartos - reservasOcupadas <= 0) {
            return reservaMapper.toReservaIndisponivel(reservaBuscaDto);
        }

        long dias = ChronoUnit.DAYS.between(reservaBuscaDto.dataInicio(), reservaBuscaDto.dataFim());

        BigDecimal valorReserva = categoriaQuartoService.findById(reservaBuscaDto.categoriaId())
                .getValorDiaria()
                .multiply(BigDecimal.valueOf(dias));

        return reservaMapper.toReservaDisponivel(reservaBuscaDto, valorReserva);
    }

    @Transactional
    public CancelamentoDto cancelarReserva(CancelamentoFormDto cancelamentoFormDto) {
        Reserva reserva = reservaRepository.findById(cancelamentoFormDto.reservaId()).orElseThrow(
                () -> new ResourceNotFoundException("Reserva de id " + cancelamentoFormDto.reservaId() + " não encontrada")
        );

        if (reserva.getStatusReserva() != StatusReserva.RESERVADO) {
            throw new BusinessRuleException("Não é possível cancelar uma reserva que não está no status Reservado.");
        }

        reserva.setStatusReserva(StatusReserva.CANCELADO);
        Cancelamento cancelamento = cancelamentoMapper.formToEntity(cancelamentoFormDto);
        cancelamento.setReserva(reserva);
        cancelamento.setDataCancelamento(LocalDateTime.now());
        cancelamento.setValorMulta(calcularMulta(reserva, LocalDateTime.now()));

        reservaRepository.save(reserva);
        cancelamentoRepository.save(cancelamento);

        return cancelamentoMapper.toDto(cancelamento);
    }

    private BigDecimal calcularMulta(Reserva reserva, LocalDateTime dataCancelamento) {
        long horasAteCheckin = Duration.between(dataCancelamento, reserva.getDataInicio()).toHours();

        if (horasAteCheckin < 24) {
            return reserva.getValorReserva();
        } else if (horasAteCheckin < 72) {
            return reserva.getValorReserva().multiply(new BigDecimal("0.5"));
        } else {
            return BigDecimal.ZERO;
        }
    }
}
