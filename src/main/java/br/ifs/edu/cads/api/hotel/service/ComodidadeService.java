package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.rest.dto.ComodidadeDto;
import br.ifs.edu.cads.api.hotel.rest.dto.ComodidadeFormDto;
import br.ifs.edu.cads.api.hotel.rest.dto.mapper.ComodidadeMapper;
import br.ifs.edu.cads.api.hotel.entity.Comodidade;
import br.ifs.edu.cads.api.hotel.repository.ComodidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class ComodidadeService {
    private final ComodidadeRepository comodidadeRepository;
    private final ComodidadeMapper comodidadeMapper;

    ComodidadeService(ComodidadeRepository comodidadeRepository, ComodidadeMapper comodidadeMapper) {
        this.comodidadeRepository = comodidadeRepository;
        this.comodidadeMapper = comodidadeMapper;
    }

    public ComodidadeDto createComodidade(ComodidadeFormDto comodidadeFormDto) {
        Comodidade newComodidade = comodidadeRepository.save(
                comodidadeMapper.formToEntity(comodidadeFormDto)
        );

        return comodidadeMapper.toDto(newComodidade);
    }
}
