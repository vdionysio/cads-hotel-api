package br.ifs.edu.cads.api.hotel.repository;

import br.ifs.edu.cads.api.hotel.dto.QuartoReservaDto;
import br.ifs.edu.cads.api.hotel.entity.Quarto;
import br.ifs.edu.cads.api.hotel.enums.StatusQuarto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuartoRepository extends JpaRepository<Quarto, Integer> {
    @Query("""
        SELECT new br.ifs.edu.cads.api.hotel.dto.QuartoReservaDto(q, r)
        FROM Quarto q
        LEFT JOIN Reserva r ON r.quarto.id = q.id
            /* O JOIN continua trazendo Checkin e Checkout para análise */
            AND (r.statusReserva = 'CHECKIN' OR r.statusReserva = 'CHECKOUT')
            AND (
                :statusFiltro = 'CHECKIN' OR 
                :statusFiltro = 'CHECKOUT' OR 
                :statusFiltro = 'OCUPADO'
            )
        WHERE (:idCategoriaQuarto IS NULL OR q.categoria.id = :idCategoriaQuarto)
        AND (
            /* 1. CHECKIN: Normal, quem está lá agora */
            (:statusFiltro = 'CHECKIN' AND r.statusReserva = 'CHECKIN') OR

            /* 2. CHECKOUT (CORRIGIDO): 
               Só mostra se saiu e a data fim for HOJE (CURRENT_DATE).
               Se saiu ontem, o quarto volta a ser apenas um quarto DISPONIVEL normal.
            */
            (:statusFiltro = 'CHECKOUT' 
                AND r.statusReserva = 'CHECKOUT' 
                AND r.dataFim = CURRENT_DATE 
                AND q.statusQuarto <> 'OCUPADO') OR

            /* 3. Status Físicos (Disponível, Bloqueado) */
            (:statusFiltro = 'DISPONIVEL' AND q.statusQuarto = 'DISPONIVEL') OR
            (:statusFiltro = 'BLOQUEADO' AND q.statusQuarto = 'BLOQUEADO') OR
            
            /* 4. OCUPADO: Traz o status físico, ignorando histórico */
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
