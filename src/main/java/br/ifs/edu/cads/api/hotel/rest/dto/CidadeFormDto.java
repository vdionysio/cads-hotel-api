package br.ifs.edu.cads.api.hotel.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CidadeFormDto(
        @NotBlank
        String nome,

        @NotBlank
        @Size(min = 2, max = 2)
        String uf
) {
}
