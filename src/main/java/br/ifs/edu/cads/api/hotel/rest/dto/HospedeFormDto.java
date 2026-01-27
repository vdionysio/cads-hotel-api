package br.ifs.edu.cads.api.hotel.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record HospedeFormDto(

        @NotBlank
        String nome,

        @NotNull
        @CPF
        String cpf,

        @NotNull
        LocalDate dataNascimento,

        @NotNull
        // TODO: validação formato telefone
        String telefone,

        @NotNull
        Long cidadeId
        ) {
}
