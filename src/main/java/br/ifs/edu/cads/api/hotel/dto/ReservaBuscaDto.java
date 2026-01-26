package br.ifs.edu.cads.api.hotel.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReservaBuscaDto (
        @NotNull
        LocalDateTime dataInicio,

        @NotNull
        LocalDateTime dataFim,

        @NotNull
        Integer categoriaId
){}
