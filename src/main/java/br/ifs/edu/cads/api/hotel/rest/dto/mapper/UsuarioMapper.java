package br.ifs.edu.cads.api.hotel.rest.dto.mapper;

import br.ifs.edu.cads.api.hotel.rest.dto.UsuarioDto;
import br.ifs.edu.cads.api.hotel.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    public UsuarioDto toDto(Usuario usuario) {
        return new UsuarioDto(
                usuario.getEmail(), usuario.getPapel(), usuario.getAtivo()
        );
    }
}
