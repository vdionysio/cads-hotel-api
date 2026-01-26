package br.ifs.edu.cads.api.hotel.dto;

import br.ifs.edu.cads.api.hotel.enums.FormaPagamento;
import br.ifs.edu.cads.api.hotel.enums.StatusReserva;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReservaDto(
        Long id,
        LocalDate dataInicio,
        LocalDate dataFim,
        BigDecimal valorReserva,
        StatusReserva status,
        FormaPagamento formaPagamento,
        Integer idCategoria,
        String nomeCategoria,
        Long idHospedeTitular,
        String nomeHospedeTitular
) {
}
