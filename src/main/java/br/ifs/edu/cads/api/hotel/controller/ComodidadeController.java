package br.ifs.edu.cads.api.hotel.controller;

import br.ifs.edu.cads.api.hotel.dto.ComodidadeDto;
import br.ifs.edu.cads.api.hotel.dto.ComodidadeFormDto;
import br.ifs.edu.cads.api.hotel.service.ComodidadeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/comodidades")
public class ComodidadeController {
    private final ComodidadeService comodidadeService;

    public ComodidadeController(ComodidadeService comodidadeService) {
        this.comodidadeService = comodidadeService;
    }

    @PostMapping
    public ResponseEntity<ComodidadeDto> createComodidade(@RequestBody @Valid ComodidadeFormDto comodidadeFormDto) {
        ComodidadeDto comodidadeDto = comodidadeService.createComodidade(comodidadeFormDto);
        URI location = URI.create("api/comodidades/" + comodidadeDto.id());
        return ResponseEntity.created(location).body(comodidadeDto);
    }
}
