package br.ifs.edu.cads.api.hotel.controller;

import br.ifs.edu.cads.api.hotel.dto.HospedeDto;
import br.ifs.edu.cads.api.hotel.dto.HospedeFormDto;
import br.ifs.edu.cads.api.hotel.dto.HospedeUsuarioDto;
import br.ifs.edu.cads.api.hotel.dto.HospedeUsuarioFormDto;
import br.ifs.edu.cads.api.hotel.service.HospedeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/hospedes")
public class HospedeController {
    private final HospedeService hospedeService;

    public HospedeController(HospedeService hospedeService) {
        this.hospedeService = hospedeService;
    }

    @PostMapping
    public ResponseEntity<HospedeDto> createHospede(@RequestBody @Valid HospedeFormDto hospedeFormDto) {
        HospedeDto hospedeDto = hospedeService.createHospede(hospedeFormDto);
        URI location = URI.create("/api/hospedes" + hospedeDto.id());
        return ResponseEntity.created(location).body(hospedeDto);
    }

    @PostMapping("/usuario")
    public ResponseEntity<HospedeUsuarioDto> createHospedeUsuario(@RequestBody @Valid HospedeUsuarioFormDto hospedeUsuarioFormDto) {
        HospedeUsuarioDto hospedeUsuarioDto = hospedeService.createHospedeUsuario(hospedeUsuarioFormDto);
        URI location = URI.create("/api/hospedes" + hospedeUsuarioDto.dadosPessoais().id());
        return ResponseEntity.created(location).body(hospedeUsuarioDto);
    }
}
