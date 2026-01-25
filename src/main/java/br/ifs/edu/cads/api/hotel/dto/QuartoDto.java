package br.ifs.edu.cads.api.hotel.dto;

import br.ifs.edu.cads.api.hotel.entity.CategoriaQuarto;
import br.ifs.edu.cads.api.hotel.enums.StatusQuarto;
import jakarta.persistence.*;

public record QuartoDto(

        Integer id,

        Integer numApartamento,

        Integer numBloco,

        Integer numAndar,

        Integer categoriaQuartoId,

        StatusQuarto statusQuarto
) {
}
