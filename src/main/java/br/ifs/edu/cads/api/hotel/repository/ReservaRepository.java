package br.ifs.edu.cads.api.hotel.repository;


import br.ifs.edu.cads.api.hotel.entity.Reserva;
import br.ifs.edu.cads.api.hotel.enums.FormaPagamento;
import br.ifs.edu.cads.api.hotel.rest.dto.ReservaPagamentoRelatorioDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query("""
        SELECT COUNT(r) FROM Reserva r
        WHERE r.categoriaQuarto.id = :categoriaQuartoId
        AND r.statusReserva <> 'CANCELADO'
        AND (r.dataInicio < :dataFim AND r.dataFim > :dataInicio)
    """)
    Integer contarReservasConflitantes(
            LocalDate dataInicio, LocalDate dataFim, Integer categoriaQuartoId
    );

    List<Reserva> findReservaByDataInicioBetween(LocalDateTime dataInicioAfter, LocalDateTime dataInicioBefore);

    @Query("""
        SELECT new br.ifs.edu.cads.api.hotel.rest.dto.ReservaPagamentoRelatorioDto(
            r.formaPagamento, COUNT(r), SUM(r.valorReserva)
        )
        FROM Reserva r
        WHERE r.statusReserva = br.ifs.edu.cads.api.hotel.enums.StatusReserva.CHECKOUT
        AND r.formaPagamento = :forma
        AND r.dataInicio BETWEEN :dataInicio AND :dataFim
        GROUP BY r.formaPagamento
        ORDER BY SUM(r.valorReserva) DESC
    """)
    Page<ReservaPagamentoRelatorioDto> relatorioPorFormaPagamento(
            @Param("forma") FormaPagamento forma,
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFim") LocalDateTime dataFim,
    Pageable pageable);

    @Query("""
        SELECT r FROM Reserva r
        WHERE r.statusReserva <> br.ifs.edu.cads.api.hotel.enums.StatusReserva.CANCELADO
        AND r.dataInicio BETWEEN :dataInicio AND :dataFim
    """)
    List<Reserva> findReservasAtivas(LocalDateTime dataInicio, LocalDateTime dataFim);
}
