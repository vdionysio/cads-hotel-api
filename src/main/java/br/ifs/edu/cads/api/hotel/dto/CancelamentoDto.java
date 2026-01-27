package br.ifs.edu.cads.api.hotel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CancelamentoDto(
        Long id,
        String motivo,
        LocalDateTime dataCancelamento,
        Long reservaId
) {
}
