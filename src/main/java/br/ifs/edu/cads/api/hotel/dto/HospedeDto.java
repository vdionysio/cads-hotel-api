package br.ifs.edu.cads.api.hotel.dto;

import br.ifs.edu.cads.api.hotel.entity.Cidade;

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
