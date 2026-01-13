package br.ifs.edu.cads.api.hotel.controller;

import br.ifs.edu.cads.api.hotel.dto.FuncionarioDto;
import br.ifs.edu.cads.api.hotel.dto.FuncionarioFormDto;
import br.ifs.edu.cads.api.hotel.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioDto> createFuncionario(@RequestBody @Valid FuncionarioFormDto funcionarioFormDto) {
        FuncionarioDto funcionarioDto = funcionarioService.createFuncionario(funcionarioFormDto);
        URI location = URI.create("/api/funcionarios" + funcionarioDto.id());
        return ResponseEntity.created(location).body(funcionarioDto);
    }
}
