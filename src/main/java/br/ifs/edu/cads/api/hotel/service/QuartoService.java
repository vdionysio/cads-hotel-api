package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.dto.QuartoDto;
import br.ifs.edu.cads.api.hotel.dto.QuartoFormDto;
import br.ifs.edu.cads.api.hotel.dto.mapper.QuartoMapper;
import br.ifs.edu.cads.api.hotel.entity.CategoriaQuarto;
import br.ifs.edu.cads.api.hotel.entity.Quarto;
import br.ifs.edu.cads.api.hotel.exception.ResourceNotFoundException;
import br.ifs.edu.cads.api.hotel.repository.CategoriaQuartoRepository;
import br.ifs.edu.cads.api.hotel.repository.QuartoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class QuartoService {

    private final CategoriaQuartoRepository categoriaQuartoRepository;
    private final QuartoRepository quartoRepository;
    private final QuartoMapper quartoMapper;

    public QuartoService(CategoriaQuartoRepository categoriaQuartoRepository, QuartoRepository quartoRepository, QuartoMapper quartoMapper) {
        this.categoriaQuartoRepository = categoriaQuartoRepository;
        this.quartoRepository = quartoRepository;
        this.quartoMapper = quartoMapper;
    }

    @Transactional
    public QuartoDto createQuarto(QuartoFormDto quartoFormDto) {
        CategoriaQuarto categoriaQuarto = categoriaQuartoRepository.findById(quartoFormDto.categoriaQuartoId()).orElseThrow(
                () -> new ResourceNotFoundException("Categoria de ID " + quartoFormDto.categoriaQuartoId() + " não encontrada.")
        );

        Quarto quarto = quartoMapper.formToEntity(quartoFormDto);
        quarto.setCategoria(categoriaQuarto);

        Quarto newQuarto = quartoRepository.save(quarto);

        return quartoMapper.toDto(newQuarto);
    }

    public Integer countTotalPorCategoria(Integer categoriaId) {
        return quartoRepository.countQuartosByCategoriaId(categoriaId);
    }
}
