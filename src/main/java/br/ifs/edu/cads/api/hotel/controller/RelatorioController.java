package br.ifs.edu.cads.api.hotel.controller;

import br.ifs.edu.cads.api.hotel.dto.ReservaSimplesDto;
import br.ifs.edu.cads.api.hotel.dto.UsuarioDto;
import br.ifs.edu.cads.api.hotel.enums.PapelUsuario;
import br.ifs.edu.cads.api.hotel.repository.RelatorioService;
import br.ifs.edu.cads.api.hotel.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    private final UsuarioService usuarioService;
    private final RelatorioService relatorioService;

    public RelatorioController(UsuarioService usuarioService, RelatorioService relatorioService) {
        this.usuarioService = usuarioService;
        this.relatorioService = relatorioService;
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<ReservaSimplesDto>> getReservasPorPeriodo(
            @RequestParam("data-inicial") LocalDate dataInicial,
            @RequestParam("data-final") LocalDate dataFinal,
            @RequestHeader("usuario-email") String email,
            @RequestHeader("usuario-senha") String senha) {
        UsuarioDto usuarioDto = usuarioService.autenticarUsuario(email, senha);

        if (!(usuarioDto.papel() == PapelUsuario.GERENTE || usuarioDto.papel() == PapelUsuario.RECEPCIONISTA)) {
            throw new RuntimeException("Operação não permitida para o usuário.");
        }

        List<ReservaSimplesDto> reservas = relatorioService.getReservasPorPeriodo(dataInicial, dataFinal);

        return ResponseEntity.ok(reservas);
    }
}
