package br.ifs.edu.cads.api.hotel.dto;

import br.ifs.edu.cads.api.hotel.entity.Reserva;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CancelamentoFormDto(

        @NotBlank
        String motivo,

        @NotNull
        Long reservaId
) {
}
