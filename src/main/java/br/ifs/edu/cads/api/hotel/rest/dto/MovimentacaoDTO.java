package br.ifs.edu.cads.api.hotel.rest.dto;

import br.ifs.edu.cads.api.hotel.enums.StatusReserva;

import java.math.BigDecimal;

public record MovimentacaoDTO(
        String nomeHospede,
        String categoriaQuarto,
        StatusReserva status,
        BigDecimal valor
) {
}
