package br.ifs.edu.cads.api.hotel.dto;

import br.ifs.edu.cads.api.hotel.enums.StatusQuarto;

public record QuartoDto(

        Integer id,

        Integer numQuarto,

        Integer numBloco,

        Integer numAndar,

        Integer categoriaQuartoId,

        StatusQuarto statusQuarto
) {
}
