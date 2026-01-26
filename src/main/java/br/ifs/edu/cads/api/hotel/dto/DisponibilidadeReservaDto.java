package br.ifs.edu.cads.api.hotel.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DisponibilidadeReservaDto(
        Integer categoriaId,
        Boolean estaDisponivel,
        BigDecimal valorReserva,
        LocalDate dataInicio,
        LocalDate dataFim
) {
}
