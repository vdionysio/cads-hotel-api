package br.ifs.edu.cads.api.hotel.dto;

import br.ifs.edu.cads.api.hotel.enums.PapelUsuario;

public record UsuarioDto(
        String email,
        PapelUsuario papel,
        Boolean ativo
) {
}
