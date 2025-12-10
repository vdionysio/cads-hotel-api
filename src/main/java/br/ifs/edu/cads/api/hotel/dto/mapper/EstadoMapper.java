package br.ifs.edu.cads.api.hotel.dto.mapper;

import br.ifs.edu.cads.api.hotel.dto.EstadoDto;
import br.ifs.edu.cads.api.hotel.dto.EstadoFormDto;
import br.ifs.edu.cads.api.hotel.entity.Estado;
import org.springframework.stereotype.Component;

@Component
public class EstadoMapper {
    public static EstadoDto toDto(Estado estado) {
        return new EstadoDto(estado.getId(), estado.getNome(), estado.getUf());
    }

    public static Estado formToEntity(EstadoFormDto estadoFormDto) {
        return new Estado(estadoFormDto.nome(), estadoFormDto.uf());
    }

}
