package br.ifs.edu.cads.api.hotel.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservaBuscaDto (
        @NotNull
        LocalDate dataInicio,

        @NotNull
        LocalDate dataFim,

        @NotNull
        Integer categoriaId
){}
