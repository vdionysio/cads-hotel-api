package br.ifs.edu.cads.api.hotel.service;

import br.ifs.edu.cads.api.hotel.rest.dto.UsuarioDto;
import br.ifs.edu.cads.api.hotel.rest.dto.mapper.UsuarioMapper;
import br.ifs.edu.cads.api.hotel.entity.Usuario;
import br.ifs.edu.cads.api.hotel.exception.ResourceNotFoundException;
import br.ifs.edu.cads.api.hotel.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioDto autenticarUsuario(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Usuário não encontrado.")
        );

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new ResourceNotFoundException("Usuário ou senha inválido.");
        }

        return usuarioMapper.toDto(usuario);
    }

}
