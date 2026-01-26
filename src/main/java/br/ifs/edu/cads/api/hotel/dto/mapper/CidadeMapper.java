package br.ifs.edu.cads.api.hotel.dto.mapper;

import br.ifs.edu.cads.api.hotel.dto.CidadeDto;
import br.ifs.edu.cads.api.hotel.dto.CidadeFormDto;
import br.ifs.edu.cads.api.hotel.entity.Cidade;
import org.springframework.stereotype.Component;

@Component
public class CidadeMapper {
    public CidadeDto toDto(Cidade cidade) {
        return new CidadeDto(cidade.getId(), cidade.getNome(), cidade.getEstado().getUf());
    }

    public Cidade formToEntity(CidadeFormDto cidadeFormDto) {
        return new Cidade(cidadeFormDto.nome());
    }
}
