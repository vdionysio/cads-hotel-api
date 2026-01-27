package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.rest.dto.HospedeDto;
import br.ifs.edu.cads.api.hotel.rest.dto.HospedeFormDto;
import br.ifs.edu.cads.api.hotel.rest.dto.HospedeUsuarioDto;
import br.ifs.edu.cads.api.hotel.rest.dto.HospedeUsuarioFormDto;
import br.ifs.edu.cads.api.hotel.rest.dto.mapper.HospedeMapper;
import br.ifs.edu.cads.api.hotel.entity.Cidade;
import br.ifs.edu.cads.api.hotel.entity.Hospede;
import br.ifs.edu.cads.api.hotel.entity.Usuario;
import br.ifs.edu.cads.api.hotel.enums.PapelUsuario;
import br.ifs.edu.cads.api.hotel.exception.ResourceNotFoundException;
import br.ifs.edu.cads.api.hotel.repository.CidadeRepository;
import br.ifs.edu.cads.api.hotel.repository.HospedeRepository;
import br.ifs.edu.cads.api.hotel.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HospedeService {

    private final HospedeMapper hospedeMapper;
    private final HospedeRepository hospedeRepository;
    private final CidadeRepository cidadeRepository;
    private final UsuarioRepository usuarioRepository;

    public HospedeService(HospedeRepository hospedeRepository, CidadeRepository cidadeRepository, HospedeMapper hospedeMapper, UsuarioRepository usuarioRepository) {
        this.hospedeRepository = hospedeRepository;
        this.cidadeRepository = cidadeRepository;
        this.hospedeMapper = hospedeMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public HospedeDto createHospede(HospedeFormDto hospedeFormDto) {
        Hospede hospede = hospedeMapper.formToEntity(hospedeFormDto);

        Cidade cidade = cidadeRepository.findById(hospedeFormDto.cidadeId()).orElseThrow(
                () -> new ResourceNotFoundException("Cidade de ID " + hospedeFormDto.cidadeId() + " não encontrada.")
        );

        hospede.setCidade(cidade);

        Hospede newHospede = hospedeRepository.save(hospede);
        return hospedeMapper.toDto(newHospede);
    }

    @Transactional
    public HospedeUsuarioDto createHospedeUsuario(HospedeUsuarioFormDto hospedeUsuarioFormDto) {
        Hospede hospede = hospedeMapper.usuarioFormToEntity(hospedeUsuarioFormDto);

        Cidade cidade = cidadeRepository.findById(hospedeUsuarioFormDto.dadosPessoais().cidadeId()).orElseThrow(
                () -> new ResourceNotFoundException("Cidade de ID " + hospedeUsuarioFormDto.dadosPessoais().cidadeId() + " não encontrada.")
        );

        Usuario usuario = new Usuario(hospedeUsuarioFormDto.email(), hospedeUsuarioFormDto.senha(), PapelUsuario.HOSPEDE);

        hospede.setCidade(cidade);
        hospede.setUsuario(usuario);

        usuarioRepository.save(hospede.getUsuario());

        Hospede newHospede = hospedeRepository.save(hospede);
        return hospedeMapper.toUsuarioDto(newHospede);
    }

    public Hospede findById(Long idHospede) {
        Hospede hospede = hospedeRepository.findById(idHospede).orElseThrow(
                () -> new ResourceNotFoundException("Hospede de ID " + idHospede + " não encontrado.")
        );

        return hospede;
    }
}
