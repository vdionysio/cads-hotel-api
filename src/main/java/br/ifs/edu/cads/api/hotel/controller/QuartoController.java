package br.ifs.edu.cads.api.hotel.controller;

import br.ifs.edu.cads.api.hotel.dto.QuartoDto;
import br.ifs.edu.cads.api.hotel.dto.QuartoFormDto;
import br.ifs.edu.cads.api.hotel.service.QuartoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/quartos")
public class QuartoController {

    private final QuartoService quartoService;

    public QuartoController(QuartoService quartoService) {
        this.quartoService = quartoService;
    }

    @PostMapping
    public ResponseEntity<QuartoDto> createQuarto(@RequestBody @Valid QuartoFormDto quartoFormDto) {
        QuartoDto quartoDto = quartoService.createQuarto(quartoFormDto);
        URI location = URI.create("/api/quartos/" + quartoDto.id());
        return ResponseEntity.created(location).body(quartoDto);
    }
}
