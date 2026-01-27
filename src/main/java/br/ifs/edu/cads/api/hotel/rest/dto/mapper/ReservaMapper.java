package br.ifs.edu.cads.api.hotel.rest.dto.mapper;

import br.ifs.edu.cads.api.hotel.entity.Reserva;
import br.ifs.edu.cads.api.hotel.enums.StatusReserva;
import br.ifs.edu.cads.api.hotel.rest.dto.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ReservaMapper {
    public Reserva formToEntity(ReservaFormDto reservaFormDto) {
        return new Reserva(
                reservaFormDto.dataInicio().atTime(14, 0),
                reservaFormDto.dataFim().atTime(12, 0),
                reservaFormDto.formaPagamento(),
                StatusReserva.RESERVADO
        );
    }

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

    public ReservaDto toDto(Reserva reserva) {
        return new ReservaDto(
                reserva.getId(),
                reserva.getDataInicio(),
                reserva.getDataFim(),
                reserva.getValorReserva(),
                reserva.getStatusReserva(),
                reserva.getFormaPagamento(),
                reserva.getCategoriaQuarto().getId(),
                reserva.getCategoriaQuarto().getNome(),
                reserva.getHospede().getId(),
                reserva.getHospede().getNome()
        );
    }

    public ReservaSimplesDto toDtoSimples(Reserva reserva) {
        return new ReservaSimplesDto(
                reserva.getId(),
                reserva.getHospede().getNome(),
                reserva.getCategoriaQuarto().getNome(),
                reserva.getDataInicio(),
                reserva.getDataFim(),
                reserva.getStatusReserva()
        );
    }
}
