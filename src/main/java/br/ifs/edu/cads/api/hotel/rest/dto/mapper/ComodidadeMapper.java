package br.ifs.edu.cads.api.hotel.rest.dto.mapper;

import br.ifs.edu.cads.api.hotel.rest.dto.ComodidadeDto;
import br.ifs.edu.cads.api.hotel.rest.dto.ComodidadeFormDto;
import br.ifs.edu.cads.api.hotel.entity.Comodidade;
import org.springframework.stereotype.Component;

@Component
public class ComodidadeMapper {
    public ComodidadeDto toDto(Comodidade comodidade) {
        return new ComodidadeDto(
                comodidade.getId(),
                comodidade.getNome(),
                comodidade.getDescricao()
        );
    }

    public Comodidade formToEntity(ComodidadeFormDto comodidadeFormDto) {
        return new Comodidade(
                comodidadeFormDto.nome(),
                comodidadeFormDto.descricao()
        );
    }
}
