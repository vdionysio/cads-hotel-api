package br.ifs.edu.cads.api.hotel.rest.dto.mapper;

import br.ifs.edu.cads.api.hotel.entity.Estado;
import br.ifs.edu.cads.api.hotel.rest.dto.CidadeDto;
import br.ifs.edu.cads.api.hotel.rest.dto.CidadeFormDto;
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

    public void updateEntity(CidadeFormDto dto, Cidade cidade, Estado estado) {
        cidade.setNome(dto.nome());
        cidade.setEstado(estado);
    }
}
