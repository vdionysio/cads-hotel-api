package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.dto.UsuarioDto;
import br.ifs.edu.cads.api.hotel.dto.mapper.UsuarioMapper;
import br.ifs.edu.cads.api.hotel.entity.Usuario;
import br.ifs.edu.cads.api.hotel.exception.ResourceNotFoundException;
import br.ifs.edu.cads.api.hotel.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioDto autenticarUsuario(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado.")
        );

        if (!usuario.getSenha().equals(senha)) {
            throw new ResourceNotFoundException("Usuário ou senha inválido.");
        }

        return usuarioMapper.toDto(usuario);
    }
}
