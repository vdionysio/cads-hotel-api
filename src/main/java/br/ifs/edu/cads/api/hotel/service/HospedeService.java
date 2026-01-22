package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.dto.HospedeDto;
import br.ifs.edu.cads.api.hotel.dto.HospedeFormDto;
import br.ifs.edu.cads.api.hotel.dto.mapper.HospedeMapper;
import br.ifs.edu.cads.api.hotel.entity.Cidade;
import br.ifs.edu.cads.api.hotel.entity.Hospede;
import br.ifs.edu.cads.api.hotel.exception.ResourceNotFoundException;
import br.ifs.edu.cads.api.hotel.repository.CidadeRepository;
import br.ifs.edu.cads.api.hotel.repository.HospedeRepository;
import org.springframework.stereotype.Service;

@Service
public class HospedeService {

    private final HospedeMapper hospedeMapper;
    private final HospedeRepository hospedeRepository;
    private final CidadeRepository cidadeRepository;

    public HospedeService(HospedeRepository hospedeRepository, CidadeRepository cidadeRepository, HospedeMapper hospedeMapper) {
        this.hospedeRepository = hospedeRepository;
        this.cidadeRepository = cidadeRepository;
        this.hospedeMapper = hospedeMapper;
    }

    public HospedeDto createHospede(HospedeFormDto hospedeFormDto) {
        Hospede hospede = hospedeMapper.formToEntity(hospedeFormDto);

        Cidade cidade = cidadeRepository.findById(hospedeFormDto.cidadeId()).orElseThrow(
                () -> new ResourceNotFoundException("Cidade de ID " + hospedeFormDto.cidadeId() + " não encontrada.")
        );

        hospede.setCidade(cidade);

        Hospede newHospede = hospedeRepository.save(hospede);
        return hospedeMapper.toDto(newHospede);
    }
}
