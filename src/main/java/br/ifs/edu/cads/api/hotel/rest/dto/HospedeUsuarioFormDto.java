package br.ifs.edu.cads.api.hotel.rest.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record HospedeUsuarioFormDto(
        @NotNull
        @Valid
        @JsonUnwrapped
        HospedeFormDto dadosPessoais,

        @NotNull
        @Email
        String email,

        @NotEmpty
        @Size(min = 8, max = 8)
        String senha
) {
}
