package br.ifs.edu.cads.api.hotel.rest.dto;


import br.ifs.edu.cads.api.hotel.enums.PosicaoQuarto;

import java.math.BigDecimal;
import java.util.List;

public record CategoriaQuartoDto(

        Integer id,

        String nome,

        String descricao,

        Integer maxHospedes,

        BigDecimal valorDiaria,

        PosicaoQuarto posicaoQuarto,

        List<ComodidadeDto> comodidades
) {
}
