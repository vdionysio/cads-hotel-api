package br.ifs.edu.cads.api.hotel.rest.dto;

import java.time.LocalDate;

public record HospedeUsuarioRelatorioDto(

        String nome,

        String email,

        String telefone,

        String cidadeUf
) {
}
