package br.ifs.edu.cads.api.hotel.repository;

import br.ifs.edu.cads.api.hotel.rest.dto.QuartoReservaDto;
import br.ifs.edu.cads.api.hotel.entity.Quarto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuartoRepository extends JpaRepository<Quarto, Integer> {
    @Query("""
        SELECT new br.ifs.edu.cads.api.hotel.rest.dto.QuartoReservaDto(q, r)
        FROM Quarto q
        LEFT JOIN Reserva r ON r.quarto.id = q.id
            AND (r.statusReserva = 'CHECKIN' OR r.statusReserva = 'CHECKOUT')
            AND (
                :statusFiltro = 'CHECKIN' OR
                :statusFiltro = 'CHECKOUT' OR
                :statusFiltro = 'OCUPADO'
            )
        WHERE (:idCategoriaQuarto IS NULL OR q.categoria.id = :idCategoriaQuarto)
        AND (
            /* CHECKIN */
            (:statusFiltro = 'CHECKIN' AND r.statusReserva = 'CHECKIN') OR

            /* CHECKOUT */
            (:statusFiltro = 'CHECKOUT'
                AND r.statusReserva = 'CHECKOUT'
                AND r.dataFim = CURRENT_DATE
                AND q.statusQuarto <> 'OCUPADO') OR

            /* Status Físico */
            (:statusFiltro = 'DISPONIVEL' AND q.statusQuarto = 'DISPONIVEL') OR
            (:statusFiltro = 'BLOQUEADO' AND q.statusQuarto = 'BLOQUEADO') OR

            (:statusFiltro = 'OCUPADO' AND q.statusQuarto = 'OCUPADO'
                AND (r.id IS NULL OR r.statusReserva = 'CHECKIN')
            )
        )
    """)
    Page<QuartoReservaDto> findParaRelatorioOcupacao(
            @Param("idCategoriaQuarto") Integer idCategoriaQuarto,
            @Param("statusFiltro") String statusFiltro,
            Pageable pageable);

    Integer countQuartosByCategoriaId(Integer categoriaId);
}
