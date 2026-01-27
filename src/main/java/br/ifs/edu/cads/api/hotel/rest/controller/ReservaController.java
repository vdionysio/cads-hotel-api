package br.ifs.edu.cads.api.hotel.rest.controller;

import br.ifs.edu.cads.api.hotel.rest.dto.*;
import br.ifs.edu.cads.api.hotel.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<ReservaDto> criarReserva(@RequestBody @Valid ReservaFormDto reservaFormDto) {
        ReservaDto reservaDto = reservaService.criarReserva(reservaFormDto);
        URI location = URI.create("/api/cidades/" + reservaDto.id());
        return ResponseEntity.created(location).body(reservaDto);
    }

    @PostMapping("/disponibilidade")
    public ResponseEntity<DisponibilidadeReservaDto> checarDisponibilidade(@RequestBody @Valid ReservaBuscaDto reservaBuscaDto) {
        DisponibilidadeReservaDto disponibilidadeReservaDto = reservaService.checarDisponibilidade(reservaBuscaDto);

        return ResponseEntity.ok(disponibilidadeReservaDto);
    }

    @PostMapping("/cancelamento")
    public ResponseEntity<CancelamentoDto> cancelarReserva(@RequestBody @Valid CancelamentoFormDto cancelamentoFormDto) {
        // TODO: adicionar validação de usuário. O próprio hóspede ou adm.
        CancelamentoDto cancelamentoDto = reservaService.cancelarReserva(cancelamentoFormDto);

        return ResponseEntity.ok(cancelamentoDto);
    }
}
