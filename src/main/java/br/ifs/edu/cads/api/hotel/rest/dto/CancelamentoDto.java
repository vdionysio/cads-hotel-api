package br.ifs.edu.cads.api.hotel.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CancelamentoDto(
        Long id,
        String motivo,
        LocalDateTime dataCancelamento,
        BigDecimal valorMulta,
        Long reservaId
) {
}
