package br.ifs.edu.cads.api.hotel.repository;

import br.ifs.edu.cads.api.hotel.entity.Cancelamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CancelamentoRepository extends JpaRepository<Cancelamento, Long> {
}
