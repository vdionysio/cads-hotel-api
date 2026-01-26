package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.dto.DisponibilidadeReservaDto;
import br.ifs.edu.cads.api.hotel.dto.ReservaBuscaDto;
import br.ifs.edu.cads.api.hotel.dto.ReservaDto;
import br.ifs.edu.cads.api.hotel.dto.ReservaFormDto;
import br.ifs.edu.cads.api.hotel.dto.mapper.ReservaMapper;
import br.ifs.edu.cads.api.hotel.entity.CategoriaQuarto;
import br.ifs.edu.cads.api.hotel.entity.Funcionario;
import br.ifs.edu.cads.api.hotel.entity.Hospede;
import br.ifs.edu.cads.api.hotel.entity.Reserva;
import br.ifs.edu.cads.api.hotel.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final QuartoService quartoService;
    private final ReservaMapper reservaMapper;
    private final CategoriaQuartoService categoriaQuartoService;
    private final HospedeService hospedeService;
    private final FuncionarioService funcionarioService;

    public ReservaService(ReservaRepository reservaRepository, QuartoService quartoService, ReservaMapper reservaMapper, CategoriaQuartoService categoriaQuartoService, HospedeService hospedeService, FuncionarioService funcionarioService) {
        this.reservaRepository = reservaRepository;
        this.quartoService = quartoService;
        this.reservaMapper = reservaMapper;
        this.categoriaQuartoService = categoriaQuartoService;
        this.hospedeService = hospedeService;
        this.funcionarioService = funcionarioService;
    }

    public ReservaDto criarReserva(ReservaFormDto reservaFormDto) {
        DisponibilidadeReservaDto disponibilidadeReservaDto = checarDisponibilidade(
                new ReservaBuscaDto(
                        reservaFormDto.dataInicio(),
                        reservaFormDto.dataFim(),
                        reservaFormDto.idCategoriaQuarto())
        );

        if (!disponibilidadeReservaDto.estaDisponivel()) {
            // TODO: Criar exception customizada
            throw new RuntimeException("Quarto não disponível para o período selecionado.");
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
}
