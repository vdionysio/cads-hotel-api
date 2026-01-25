package br.ifs.edu.cads.api.hotel.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ComodidadeFormDto(
        @NotBlank
        String nome,

        @NotBlank
        @Size(min = 4)
        String descricao
) {
}
