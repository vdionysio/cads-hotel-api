package br.ifs.edu.cads.api.hotel.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DisponibilidadeReservaDto(
        Integer categoriaId,
        Boolean estaDisponivel,
        BigDecimal valorReserva,
        LocalDateTime dataCheckIn,
        LocalDateTime dataCheckOut
) {
}
