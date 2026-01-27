package br.ifs.edu.cads.api.hotel.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CancelamentoComMultaDto(
        String nomeHospede,
        String categoriaQuarto,
        LocalDateTime dataCancelamento,
        BigDecimal valorMulta
) {
}
