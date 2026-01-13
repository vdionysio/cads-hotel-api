package br.ifs.edu.cads.api.hotel.repository;

import br.ifs.edu.cads.api.hotel.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
