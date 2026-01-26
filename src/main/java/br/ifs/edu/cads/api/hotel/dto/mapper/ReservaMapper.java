package br.ifs.edu.cads.api.hotel.dto.mapper;

import br.ifs.edu.cads.api.hotel.dto.DisponibilidadeReservaDto;
import br.ifs.edu.cads.api.hotel.dto.ReservaBuscaDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class ReservaMapper {
    public DisponibilidadeReservaDto toReservaDisponivel(ReservaBuscaDto reservaBuscaDto, BigDecimal valorReserva) {
        return new DisponibilidadeReservaDto(
                reservaBuscaDto.categoriaId(),
                true,
                valorReserva,
                reservaBuscaDto.dataInicio(),
                reservaBuscaDto.dataFim());
    }

    public DisponibilidadeReservaDto toReservaIndisponivel(ReservaBuscaDto reservaBuscaDto) {
        return new DisponibilidadeReservaDto(
                reservaBuscaDto.categoriaId(),
                false,
                null,
                reservaBuscaDto.dataInicio(),
                reservaBuscaDto.dataFim());
    }
}
