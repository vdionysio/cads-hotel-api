package br.ifs.edu.cads.api.hotel.rest.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record RelatorioGerencialDTO(
        ResumoGeralDTO resumo,
        List<RelatorioUtilizacaoCategoriaDto> rentabilidadePorCategoria,
        Page<MovimentacaoDTO> movimentacoesHospedes
) {
}
