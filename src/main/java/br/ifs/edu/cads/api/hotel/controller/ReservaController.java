package br.ifs.edu.cads.api.hotel.controller;

import br.ifs.edu.cads.api.hotel.dto.DisponibilidadeReservaDto;
import br.ifs.edu.cads.api.hotel.dto.ReservaBuscaDto;
import br.ifs.edu.cads.api.hotel.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping("/disponibilidade")
    public ResponseEntity<DisponibilidadeReservaDto> checarDisponibilidade(@RequestBody @Valid ReservaBuscaDto reservaBuscaDto) {
        DisponibilidadeReservaDto disponibilidadeReservaDto = reservaService.checarDisponibilidade(reservaBuscaDto);

        return ResponseEntity.ok(disponibilidadeReservaDto);
    }
}
