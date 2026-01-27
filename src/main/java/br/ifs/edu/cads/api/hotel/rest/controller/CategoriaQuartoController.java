package br.ifs.edu.cads.api.hotel.rest.controller;

import br.ifs.edu.cads.api.hotel.rest.dto.CategoriaQuartoDto;
import br.ifs.edu.cads.api.hotel.rest.dto.CategoriaQuartoFormDto;
import br.ifs.edu.cads.api.hotel.service.CategoriaQuartoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/categoria-quartos")
public class CategoriaQuartoController {

    private final CategoriaQuartoService categoriaQuartoService;

    public CategoriaQuartoController(CategoriaQuartoService categoriaQuartoService) {
        this.categoriaQuartoService = categoriaQuartoService;
    }

    @PostMapping
    public ResponseEntity<CategoriaQuartoDto> createCategoriaQuarto(@RequestBody @Valid CategoriaQuartoFormDto categoriaQuartoFormDto) {
        CategoriaQuartoDto categoriaQuartoDto = categoriaQuartoService.createCategoriaQuarto(categoriaQuartoFormDto);
        URI location = URI.create("/api/categoria-quartos/" + categoriaQuartoDto.id());
        return ResponseEntity.created(location).body(categoriaQuartoDto);
    }
}
