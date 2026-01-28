package br.ifs.edu.cads.api.hotel.rest.dto;

import br.ifs.edu.cads.api.hotel.enums.FormaPagamento;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservaFormDto(

        @NotNull
        @Future
        LocalDate dataInicio,

        @NotNull
        @Future
        LocalDate dataFim,

        @NotNull
        FormaPagamento formaPagamento,

        @NotNull
        Integer idCategoriaQuarto,

        @NotNull
        Long idHospede,

        @NotNull
        Long idFuncionario
) {

    @AssertTrue
    public boolean isDataFimValida() {
        if (dataInicio == null || dataFim == null) return true; // nao sobrepõe o notnull
        return dataFim.isAfter(dataInicio);
    }
}
