package br.ifs.edu.cads.api.hotel.rest.dto;

import br.ifs.edu.cads.api.hotel.enums.StatusReserva;

import java.time.LocalDateTime;

public record ReservaSimplesDto(
        Long idReserva,
        String nomeHospede,
        String categoriaQuarto,
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        StatusReserva statusReserva
) {
}
