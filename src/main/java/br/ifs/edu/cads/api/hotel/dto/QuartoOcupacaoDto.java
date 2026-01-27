package br.ifs.edu.cads.api.hotel.dto;

import br.ifs.edu.cads.api.hotel.enums.StatusQuarto;

public record QuartoOcupacaoDto(
        Integer numQuarto,

        String numBlocoAndar,

        String categoriaQuarto,

        String statusAtual
) {
}
