package br.ifs.edu.cads.api.hotel.rest.dto;

import java.math.BigDecimal;

public record ResumoGeralDTO(
        long totalReservas,
        long totalCancelamentos,
        BigDecimal faturamentoBruto,
        BigDecimal faturamentoLiquido,
        String taxaOcupacaoMedia
) {}