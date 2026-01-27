package br.ifs.edu.cads.api.hotel.rest.dto.mapper;

import br.ifs.edu.cads.api.hotel.rest.dto.EstadoDto;
import br.ifs.edu.cads.api.hotel.rest.dto.EstadoFormDto;
import br.ifs.edu.cads.api.hotel.entity.Estado;
import org.springframework.stereotype.Component;

@Component
public class EstadoMapper {
    public EstadoDto toDto(Estado estado) {
        return new EstadoDto(estado.getId(), estado.getNome(), estado.getUf());
    }

    public Estado toEntity(EstadoFormDto estadoFormDto) {
        return new Estado(estadoFormDto.nome(), estadoFormDto.uf().toUpperCase());
    }

    public void updateEntity(EstadoFormDto dto, Estado estado) {
        estado.setNome(dto.nome());
        estado.setUf(dto.uf() != null ? dto.uf().toUpperCase() : null);
    }
}
