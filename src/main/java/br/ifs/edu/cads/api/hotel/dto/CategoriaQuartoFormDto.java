package br.ifs.edu.cads.api.hotel.dto;

import br.ifs.edu.cads.api.hotel.enums.PosicaoQuarto;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record  CategoriaQuartoFormDto(

        @NotBlank
        String nome,

        @NotBlank
        String descricao,

        @NotNull
        @Min(1)
        @Max(8)
        Integer maxHospedes,

        @NotNull
        BigDecimal valorDiaria,

        @NotNull
        PosicaoQuarto posicaoQuarto,

        List<Integer> comodidadesIds
) {
}
