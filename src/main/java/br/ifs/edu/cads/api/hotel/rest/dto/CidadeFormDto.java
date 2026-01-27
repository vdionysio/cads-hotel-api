package br.ifs.edu.cads.api.hotel.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CidadeFormDto(
        @NotBlank(message = "Nome é obrigatório.")
        String nome,

        @NotBlank(message = "A UF é obrigatória")
        @Size(min = 2, max = 2, message = "A UF deve possuir exatamente 2 caracteres.")
        String uf
) {
}
