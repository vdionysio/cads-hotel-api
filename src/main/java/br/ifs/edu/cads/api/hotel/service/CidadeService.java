package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.dto.CidadeDto;
import br.ifs.edu.cads.api.hotel.dto.CidadeFormDto;
import br.ifs.edu.cads.api.hotel.dto.mapper.CidadeMapper;
import br.ifs.edu.cads.api.hotel.entity.Cidade;
import br.ifs.edu.cads.api.hotel.entity.Estado;
import br.ifs.edu.cads.api.hotel.exception.ResourceNotFoundException;
import br.ifs.edu.cads.api.hotel.repository.CidadeRepository;
import br.ifs.edu.cads.api.hotel.repository.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;
    private final CidadeMapper cidadeMapper;


    public CidadeService(CidadeRepository cidadeRepository, EstadoRepository estadoRepository, CidadeMapper cidadeMapper) {
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
        this.cidadeMapper = cidadeMapper;
    }

    public List<CidadeDto> findCidadesByUf(String uf) {
        List<CidadeDto> cidades = cidadeRepository.findByEstadoUf(uf).stream()
                .map(cidadeMapper::toDto)
                .sorted(Comparator.comparing(CidadeDto::uf))
                .toList();

        return cidades;
    }

    public CidadeDto createCidade(CidadeFormDto cidadeFormDto) {
        Estado estado = estadoRepository.findByUf(cidadeFormDto.uf()).orElseThrow(
                () -> new ResourceNotFoundException("UF " + cidadeFormDto.uf() + " não encontrada.")
        );
        Cidade newCidade = cidadeRepository.save(cidadeMapper.formToEntity(cidadeFormDto, estado));

        return cidadeMapper.toDto(newCidade);
    }

    public void updateCidade(CidadeFormDto cidadeFormDto, Long id) {
        Estado estado = estadoRepository.findByUf(cidadeFormDto.uf()).orElseThrow(
                () -> new ResourceNotFoundException("UF " + cidadeFormDto.uf() + " não encontrada")
        );
        Cidade cidade = cidadeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cidade de id " + id + " não encontrada")
        );

        cidade.setNome(cidadeFormDto.nome());
        cidade.setEstado(estado);

        cidadeRepository.save(cidade);
    }

    public CidadeDto findById(Long id) {
        Cidade cidade = cidadeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cidade de id " + id + " não encontrada")
        );

        return cidadeMapper.toDto(cidade);
    }
}
