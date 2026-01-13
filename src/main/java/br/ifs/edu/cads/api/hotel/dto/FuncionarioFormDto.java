package br.ifs.edu.cads.api.hotel.dto;

import br.ifs.edu.cads.api.hotel.enums.Cargo;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record FuncionarioFormDto(

        @NotBlank
        String nome,

        @NotNull
        @CPF
        String cpf,

        @NotNull
        Cargo cargo,

        //Atributos usuario
        @NotNull
        @Email
        String email,

        @NotEmpty
        @Size(min = 8, max = 8)
        String senha
) {
}
