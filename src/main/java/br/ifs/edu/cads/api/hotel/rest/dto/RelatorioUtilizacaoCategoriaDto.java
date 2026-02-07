package br.ifs.edu.cads.api.hotel.rest.dto;

public record RelatorioUtilizacaoCategoriaDto(
        String categoria,
        Long totalReservas,
        String taxaOcupacao
) {
}
