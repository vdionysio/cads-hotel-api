package br.ifs.edu.cads.api.hotel.rest.controller;

import br.ifs.edu.cads.api.hotel.rest.dto.EstadoDto;
import br.ifs.edu.cads.api.hotel.rest.dto.EstadoFormDto;
import br.ifs.edu.cads.api.hotel.service.EstadoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public ResponseEntity<List<EstadoDto>> getEstados() {
        List<EstadoDto> estados = estadoService.findAllEstados();
        return ResponseEntity.ok(estados);
    }

    @PostMapping
    public ResponseEntity<EstadoDto> createEstado(@RequestBody @Valid EstadoFormDto estadoFormDto) {
        EstadoDto estadoDto = estadoService.createEstado(estadoFormDto);
        URI location = URI.create("/api/estados/" + estadoDto.id());
        return ResponseEntity.created(location).body(estadoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEstado(@RequestBody @Valid EstadoFormDto estadoFormDto, @PathVariable Long id) {
        estadoService.updateEstado(estadoFormDto, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoDto> getEstadoById(@PathVariable Long id) {
        EstadoDto estadoDto = estadoService.findById(id);

        return ResponseEntity.ok(estadoDto);
    }
}
