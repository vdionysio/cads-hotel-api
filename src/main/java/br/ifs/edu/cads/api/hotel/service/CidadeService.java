package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.rest.dto.CidadeDto;
import br.ifs.edu.cads.api.hotel.rest.dto.CidadeFormDto;
import br.ifs.edu.cads.api.hotel.rest.dto.mapper.CidadeMapper;
import br.ifs.edu.cads.api.hotel.entity.Cidade;
import br.ifs.edu.cads.api.hotel.entity.Estado;
import br.ifs.edu.cads.api.hotel.exception.ResourceNotFoundException;
import br.ifs.edu.cads.api.hotel.repository.CidadeRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;
    private final CidadeMapper cidadeMapper;
    private final EstadoService estadoService;


    public CidadeService(CidadeRepository cidadeRepository, CidadeMapper cidadeMapper, EstadoService estadoService) {
        this.cidadeRepository = cidadeRepository;
        this.cidadeMapper = cidadeMapper;
        this.estadoService = estadoService;
    }

    public List<CidadeDto> findCidadesByUf(String uf) {
        return cidadeRepository.findByEstadoUf(uf).stream()
                .map(cidadeMapper::toDto)
                .sorted(Comparator.comparing(CidadeDto::uf))
                .toList();
    }

    public CidadeDto createCidade(CidadeFormDto cidadeFormDto) {
        Estado estado = estadoService.findByUf(cidadeFormDto.uf());
        Cidade cidade = cidadeRepository.save(cidadeMapper.formToEntity(cidadeFormDto));
        cidade.setEstado(estado);

        return cidadeMapper.toDto(cidade);
    }

    public void updateCidade(CidadeFormDto cidadeFormDto, Long id) {
        Estado estado = estadoService.findByUf(cidadeFormDto.uf());
        Cidade cidade = cidadeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cidade de id " + id + " não encontrada")
        );

        cidadeMapper.updateEntity(cidadeFormDto, cidade, estado);

        cidadeRepository.save(cidade);
    }

    public CidadeDto findById(Long id) {
        Cidade cidade = cidadeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cidade de id " + id + " não encontrada")
        );

        return cidadeMapper.toDto(cidade);
    }
}
