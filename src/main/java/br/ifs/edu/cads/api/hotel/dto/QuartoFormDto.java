package br.ifs.edu.cads.api.hotel.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record QuartoFormDto(

        @NotNull
        @Min(1)
        Integer numApartamento,

        @NotNull
        @Min(1)
        @Max(8)
        Integer numBloco,

        @NotNull
        @Min(1)
        @Max(10)
        Integer numAndar,

        @NotNull
        Integer categoriaQuartoId
) {
}
