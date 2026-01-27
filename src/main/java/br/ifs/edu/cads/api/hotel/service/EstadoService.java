package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.rest.dto.EstadoDto;
import br.ifs.edu.cads.api.hotel.rest.dto.EstadoFormDto;
import br.ifs.edu.cads.api.hotel.rest.dto.mapper.EstadoMapper;
import br.ifs.edu.cads.api.hotel.entity.Estado;
import br.ifs.edu.cads.api.hotel.exception.ResourceNotFoundException;
import br.ifs.edu.cads.api.hotel.repository.EstadoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;
    private final EstadoMapper estadoMapper;

    public EstadoService(EstadoRepository estadoRepository, EstadoMapper estadoMapper) {
        this.estadoRepository = estadoRepository;
        this.estadoMapper = estadoMapper;
    }

    public List<EstadoDto> findAllEstados() {
        return estadoRepository.findAll(Sort.by("uf")).stream()
                .map(estadoMapper::toDto)
                .toList();
    }

    public EstadoDto createEstado(EstadoFormDto estadoFormDto) {
        Estado estado = estadoRepository.save(estadoMapper.toEntity(estadoFormDto));

        return estadoMapper.toDto(estado);
    }

    public void updateEstado(EstadoFormDto estadoFormDto, long id) {
        Estado estado = estadoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Estado de id " + id + " não encontrado")
        );

        estadoMapper.updateEntity(estadoFormDto, estado);

        estadoRepository.save(estado);
    }

    public EstadoDto findById(Long id) {
        Estado estado = estadoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Estado de id " + id + " não encontrado")
        );

        return estadoMapper.toDto(estado);
    }

    public Estado findByUf(String uf) {
        return estadoRepository.findByUf(uf).orElseThrow(
                () -> new ResourceNotFoundException("UF " + uf + " não encontrada.")
        );
    }
}
