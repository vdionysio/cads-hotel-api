package br.ifs.edu.cads.api.hotel.rest.dto;

import java.math.BigDecimal;

public record FaturamentoDTO(
        BigDecimal totalBruto,
        BigDecimal totalDescontos,
        BigDecimal totalLiquido
) {
}
