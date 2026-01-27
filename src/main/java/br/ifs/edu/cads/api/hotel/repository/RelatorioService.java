package br.ifs.edu.cads.api.hotel.repository;

import br.ifs.edu.cads.api.hotel.dto.ReservaSimplesDto;
import br.ifs.edu.cads.api.hotel.dto.mapper.ReservaMapper;
import br.ifs.edu.cads.api.hotel.entity.Reserva;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RelatorioService {

    private final ReservaRepository reservaRepository;
    private final ReservaMapper reservaMapper;

    public RelatorioService(ReservaRepository reservaRepository, ReservaMapper reservaMapper) {
        this.reservaRepository = reservaRepository;
        this.reservaMapper = reservaMapper;
    }

    public List<ReservaSimplesDto> getReservasPorPeriodo(LocalDate dataInicial, LocalDate dataFinal) {
        List<Reserva> reservas = reservaRepository.findReservaByDataInicioBetween(dataInicial, dataFinal);

        return reservas.stream().map(reservaMapper::toDtoSimples).toList();
    }
}
