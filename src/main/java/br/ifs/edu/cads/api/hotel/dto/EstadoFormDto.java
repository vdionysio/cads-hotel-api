package br.ifs.edu.cads.api.hotel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EstadoFormDto (
        @NotBlank
        String nome,

        @NotBlank
        @Size(min = 2, max = 2)
        String uf
) {
}
