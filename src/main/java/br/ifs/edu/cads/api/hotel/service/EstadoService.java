package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.dto.EstadoDto;
import br.ifs.edu.cads.api.hotel.dto.EstadoFormDto;
import br.ifs.edu.cads.api.hotel.dto.mapper.EstadoMapper;
import br.ifs.edu.cads.api.hotel.entity.Estado;
import br.ifs.edu.cads.api.hotel.exception.ResourceNotFoundException;
import br.ifs.edu.cads.api.hotel.repository.EstadoRepository;
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
        List<EstadoDto> estados = estadoRepository.findAll().stream()
                .map(estadoMapper::toDto)
                .sorted(Comparator.comparing(EstadoDto::uf))
                .toList();

        return estados;
    }

    public EstadoDto createEstado(EstadoFormDto estadoFormDto) {
        Estado newEstado = estadoRepository.save(estadoMapper.formToEntity(estadoFormDto));

        return estadoMapper.toDto(newEstado);
    }

    public void updateEstado(EstadoFormDto estadoFormDto, long id) {
        Estado estado = estadoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Estado de id " + id + " não encontrado")
        );

        estado.setNome(estadoFormDto.nome());
        estado.setUf(estadoFormDto.uf());

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
