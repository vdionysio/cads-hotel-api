package br.ifs.edu.cads.api.hotel.repository;

import br.ifs.edu.cads.api.hotel.entity.Cancelamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface CancelamentoRepository extends JpaRepository<Cancelamento, Long> {

    Page<Cancelamento> findByDataCancelamentoBetweenAndValorMultaGreaterThan(
            LocalDateTime inicio,
            LocalDateTime fim,
            BigDecimal valorMinimo,
            Pageable pageable
    );
}
