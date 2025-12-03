package br.ifs.edu.cads.api.hotel.dto.mapper;

import br.ifs.edu.cads.api.hotel.dto.EstadoDto;
import br.ifs.edu.cads.api.hotel.entity.Estado;
import org.springframework.stereotype.Component;

@Component
public class EstadoMapper {
    public static EstadoDto toDto(Estado estado) {
        return new EstadoDto(estado.getNome(), estado.getUf());
    }
}
