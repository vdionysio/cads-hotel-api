package br.ifs.edu.cads.api.hotel.dto.mapper;

import br.ifs.edu.cads.api.hotel.dto.*;
import br.ifs.edu.cads.api.hotel.entity.Reserva;
import br.ifs.edu.cads.api.hotel.enums.StatusReserva;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class ReservaMapper {
    public Reserva formToEntity(ReservaFormDto reservaFormDto) {
        return new Reserva(
                reservaFormDto.dataInicio(),
                reservaFormDto.dataFim(),
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
