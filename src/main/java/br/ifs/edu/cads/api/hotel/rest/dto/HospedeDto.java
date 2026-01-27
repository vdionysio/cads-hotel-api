package br.ifs.edu.cads.api.hotel.rest.dto;

import java.time.LocalDate;

public record HospedeDto(

        Long id,

        String nome,

        String cpf,

        LocalDate dataNascimento,

        String telefone,

        String cidade,

        String uf
) {
}
