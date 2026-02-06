package br.ifs.edu.cads.api.hotel.repository;

import br.ifs.edu.cads.api.hotel.entity.Cancelamento;
import br.ifs.edu.cads.api.hotel.entity.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CancelamentoRepository extends JpaRepository<Cancelamento, Long> {

    Page<Cancelamento> findByDataCancelamentoBetweenAndValorMultaGreaterThan(
            LocalDateTime dataInicio,
            LocalDateTime dataFim,
            BigDecimal valorMinimo,
            Pageable pageable
    );

    List<Cancelamento> findByDataCancelamentoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    Optional<Cancelamento> findByReserva(Reserva reserva);
}
