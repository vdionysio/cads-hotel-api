package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.dto.FuncionarioDto;
import br.ifs.edu.cads.api.hotel.dto.FuncionarioFormDto;
import br.ifs.edu.cads.api.hotel.dto.mapper.FuncionarioMapper;
import br.ifs.edu.cads.api.hotel.entity.Funcionario;
import br.ifs.edu.cads.api.hotel.entity.Usuario;
import br.ifs.edu.cads.api.hotel.exception.ResourceNotFoundException;
import br.ifs.edu.cads.api.hotel.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, FuncionarioMapper funcionarioMapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioMapper = funcionarioMapper;
    }

    public List<FuncionarioDto> getFuncionarios() {
        List<FuncionarioDto> funcionarios = funcionarioRepository.findAll().stream()
                .map(funcionarioMapper::toDto)
                .toList();

        return funcionarios;
    }

    @Transactional
    public FuncionarioDto createFuncionario(FuncionarioFormDto funcionarioFormDto) {
        Funcionario funcionario = funcionarioMapper.formToEntity(funcionarioFormDto);

        Usuario usuario = new Usuario(funcionarioFormDto.email(), funcionarioFormDto.senha(), funcionarioFormDto.cargo());

        funcionario.setUsuario(usuario);

        Funcionario newFuncionario = funcionarioRepository.save(funcionario);
        return funcionarioMapper.toDto(newFuncionario);
    }

    @Transactional
    public FuncionarioDto findFuncionarioByEmail(String email) {
        Funcionario funcionario = funcionarioRepository.findByUsuarioEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Funcionário de email " + email + " não encontrado.")
        );

        return funcionarioMapper.toDto(funcionario);
    }
}
