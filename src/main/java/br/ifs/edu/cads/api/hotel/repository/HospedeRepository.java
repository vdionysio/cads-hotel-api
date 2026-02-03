package br.ifs.edu.cads.api.hotel.repository;

import br.ifs.edu.cads.api.hotel.entity.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospedeRepository extends JpaRepository<Hospede, Long> {
    List<Hospede> findByUsuarioAtivoTrue();
}
