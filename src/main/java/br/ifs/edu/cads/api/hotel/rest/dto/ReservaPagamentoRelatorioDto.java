package br.ifs.edu.cads.api.hotel.rest.dto;

import br.ifs.edu.cads.api.hotel.enums.FormaPagamento;

import java.math.BigDecimal;

public record ReservaPagamentoRelatorioDto(
        FormaPagamento formaPagamento,
        Long quantidadeReservas,
        BigDecimal valorTotalRecebido
) {
}
