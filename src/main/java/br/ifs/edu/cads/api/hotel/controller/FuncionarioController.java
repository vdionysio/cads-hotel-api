package br.ifs.edu.cads.api.hotel.controller;

import br.ifs.edu.cads.api.hotel.dto.FuncionarioDto;
import br.ifs.edu.cads.api.hotel.dto.FuncionarioFormDto;
import br.ifs.edu.cads.api.hotel.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDto>> getFuncionarios() {
        List<FuncionarioDto> funcionarios = funcionarioService.getFuncionarios();
        return ResponseEntity.ok(funcionarios);
    }

    @PostMapping
    public ResponseEntity<FuncionarioDto> createFuncionario(@RequestBody @Valid FuncionarioFormDto funcionarioFormDto) {
        FuncionarioDto funcionarioDto = funcionarioService.createFuncionario(funcionarioFormDto);
        URI location = URI.create("/api/funcionarios" + funcionarioDto.id());
        return ResponseEntity.created(location).body(funcionarioDto);
    }
}
