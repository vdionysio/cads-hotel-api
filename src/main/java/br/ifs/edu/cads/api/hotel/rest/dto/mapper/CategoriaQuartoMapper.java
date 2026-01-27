package br.ifs.edu.cads.api.hotel.rest.dto.mapper;

import br.ifs.edu.cads.api.hotel.rest.dto.CategoriaQuartoDto;
import br.ifs.edu.cads.api.hotel.rest.dto.CategoriaQuartoFormDto;
import br.ifs.edu.cads.api.hotel.entity.CategoriaQuarto;
import org.springframework.stereotype.Component;

@Component
public class CategoriaQuartoMapper {
    private final ComodidadeMapper comodidadeMapper;
    private final QuartoMapper quartoMapper;

    public CategoriaQuartoMapper(ComodidadeMapper comodidadeMapper, QuartoMapper quartoMapper) {
        this.comodidadeMapper = comodidadeMapper;
        this.quartoMapper = quartoMapper;
    }

    public CategoriaQuarto formToEntity(CategoriaQuartoFormDto categoriaQuartoFormDto) {
        return new CategoriaQuarto(
                categoriaQuartoFormDto.nome(),
                categoriaQuartoFormDto.descricao(),
                categoriaQuartoFormDto.maxHospedes(),
                categoriaQuartoFormDto.valorDiaria(),
                categoriaQuartoFormDto.posicaoQuarto()
        );
    }

    public CategoriaQuartoDto toDto(CategoriaQuarto categoriaQuarto) {
        return new CategoriaQuartoDto(
                categoriaQuarto.getId(),
                categoriaQuarto.getNome(),
                categoriaQuarto.getDescricao(),
                categoriaQuarto.getMaxHospedes(),
                categoriaQuarto.getValorDiaria(),
                categoriaQuarto.getPosicaoQuarto(),
                categoriaQuarto.getComodidades().stream().map(comodidadeMapper::toDto).toList()
        );
    }
}
