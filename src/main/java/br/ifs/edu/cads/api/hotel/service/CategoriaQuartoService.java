package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.rest.dto.CategoriaQuartoDto;
import br.ifs.edu.cads.api.hotel.rest.dto.CategoriaQuartoFormDto;
import br.ifs.edu.cads.api.hotel.rest.dto.mapper.CategoriaQuartoMapper;
import br.ifs.edu.cads.api.hotel.rest.dto.mapper.ComodidadeMapper;
import br.ifs.edu.cads.api.hotel.entity.CategoriaQuarto;
import br.ifs.edu.cads.api.hotel.entity.Comodidade;
import br.ifs.edu.cads.api.hotel.exception.ResourceNotFoundException;
import br.ifs.edu.cads.api.hotel.repository.CategoriaQuartoRepository;
import br.ifs.edu.cads.api.hotel.repository.ComodidadeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaQuartoService {

    private final CategoriaQuartoRepository categoriaQuartoRepository;
    private final ComodidadeRepository comodidadeRepository;
    private final ComodidadeMapper comodidadeMapper;
    private final CategoriaQuartoMapper categoriaQuartoMapper;

    public CategoriaQuartoService(CategoriaQuartoRepository categoriaQuartoRepository, ComodidadeRepository comodidadeRepository, ComodidadeMapper comodidadeMapper, CategoriaQuartoMapper categoriaQuartoMapper) {
        this.categoriaQuartoRepository = categoriaQuartoRepository;
        this.comodidadeRepository = comodidadeRepository;
        this.comodidadeMapper = comodidadeMapper;
        this.categoriaQuartoMapper = categoriaQuartoMapper;
    }

    @Transactional
    public CategoriaQuartoDto createCategoriaQuarto(CategoriaQuartoFormDto categoriaQuartoFormDto) {
        List<Comodidade> comodidades = categoriaQuartoFormDto.comodidadesIds().stream().map(
                comodidadeId -> comodidadeRepository.findById(comodidadeId).orElseThrow(
                        () -> new ResourceNotFoundException("Comodidade de ID " + comodidadeId + " não encontrada.")
                )
        ).toList();

        CategoriaQuarto categoriaQuarto = categoriaQuartoMapper.formToEntity(categoriaQuartoFormDto);
        categoriaQuarto.setComodidades(comodidades);

        CategoriaQuarto newCategoriaQuarto = categoriaQuartoRepository.save(categoriaQuarto);

        return categoriaQuartoMapper.toDto(newCategoriaQuarto);
    }

    public CategoriaQuarto findById(Integer categoriaId) {

        CategoriaQuarto categoriaQuarto = categoriaQuartoRepository.findById(categoriaId).orElseThrow(
                () -> new ResourceNotFoundException("Categoria de ID " + categoriaId + " não encontrada.")
        );

        return categoriaQuarto;
    }
}
