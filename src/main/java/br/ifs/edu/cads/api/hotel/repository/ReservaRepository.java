package br.ifs.edu.cads.api.hotel.repository;


import br.ifs.edu.cads.api.hotel.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query("""
        SELECT COUNT(r) FROM Reserva r
        WHERE r.categoriaQuarto.id = :categoriaQuartoId
        AND r.statusReserva <> 'CANCELADO'
        AND (r.dataInicio < :dataFim AND r.dataFim > :dataInicio)
    """)
    Integer contarReservasConflitantes(
            LocalDateTime dataInicio, LocalDateTime dataFim, Integer categoriaQuartoId
    );
}
