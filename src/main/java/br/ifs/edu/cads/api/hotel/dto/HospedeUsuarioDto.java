package br.ifs.edu.cads.api.hotel.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record HospedeUsuarioDto(

        @NotNull
        @Valid
        @JsonUnwrapped
        HospedeDto dadosPessoais,

        String email
) {
}
