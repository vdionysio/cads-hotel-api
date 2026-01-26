package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.dto.DisponibilidadeReservaDto;
import br.ifs.edu.cads.api.hotel.dto.ReservaBuscaDto;
import br.ifs.edu.cads.api.hotel.dto.mapper.ReservaMapper;
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

    public ReservaService(ReservaRepository reservaRepository, QuartoService quartoService, ReservaMapper reservaMapper, CategoriaQuartoService categoriaQuartoService) {
        this.reservaRepository = reservaRepository;
        this.quartoService = quartoService;
        this.reservaMapper = reservaMapper;
        this.categoriaQuartoService = categoriaQuartoService;
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
                .valorDiaria()
                .multiply(BigDecimal.valueOf(dias));

        return reservaMapper.toReservaDisponivel(reservaBuscaDto, valorReserva);
    }
}
