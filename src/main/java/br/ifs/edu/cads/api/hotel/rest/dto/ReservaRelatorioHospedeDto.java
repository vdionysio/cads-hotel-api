package br.ifs.edu.cads.api.hotel.rest.dto;

import br.ifs.edu.cads.api.hotel.enums.StatusReserva;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReservaRelatorioHospedeDto(
        LocalDateTime dataInicio,
        LocalDateTime dataFim,
        LocalDateTime dataCheckIn,
        LocalDateTime dataCheckOut,
        String categoriaQuarto,
        StatusReserva statusReserva,
        BigDecimal valorPago
) {
}
