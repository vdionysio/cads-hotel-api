package br.ifs.edu.cads.api.hotel.dto;

import br.ifs.edu.cads.api.hotel.enums.FormaPagamento;
import br.ifs.edu.cads.api.hotel.enums.StatusReserva;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservaSimplesDto(
        Long idReserva,
        String nomeHospede,
        String categoriaQuarto,
        LocalDate dataInicio,
        LocalDate dataFim,
        StatusReserva statusReserva
) {
}
