package br.ifs.edu.cads.api.hotel.repository;

import br.ifs.edu.cads.api.hotel.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
}
