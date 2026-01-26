package br.ifs.edu.cads.api.hotel.repository;

import br.ifs.edu.cads.api.hotel.entity.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuartoRepository extends JpaRepository<Quarto, Integer> {
    Integer countQuartosByCategoriaId(Integer categoriaId);
}
