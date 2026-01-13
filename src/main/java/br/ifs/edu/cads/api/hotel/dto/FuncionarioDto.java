package br.ifs.edu.cads.api.hotel.dto;

import br.ifs.edu.cads.api.hotel.enums.Cargo;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record FuncionarioDto (
    Long id,

    String nome,

    String cpf,

    Cargo cargo,

    String email
) {
}
