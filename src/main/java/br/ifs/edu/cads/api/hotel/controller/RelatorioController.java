package br.ifs.edu.cads.api.hotel.controller;

import br.ifs.edu.cads.api.hotel.dto.CancelamentoComMultaDto;
import br.ifs.edu.cads.api.hotel.dto.QuartoOcupacaoDto;
import br.ifs.edu.cads.api.hotel.dto.ReservaSimplesDto;
import br.ifs.edu.cads.api.hotel.dto.UsuarioDto;
import br.ifs.edu.cads.api.hotel.enums.PapelUsuario;
import br.ifs.edu.cads.api.hotel.enums.StatusRelatorioOcupacao;
import br.ifs.edu.cads.api.hotel.service.RelatorioService;
import br.ifs.edu.cads.api.hotel.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<List<ReservaSimplesDto>> obterRelatorioReservasPorPeriodo(
            @RequestParam("data-inicial") LocalDate dataInicial,
            @RequestParam("data-final") LocalDate dataFinal,
            @RequestHeader("usuario-email") String email,
            @RequestHeader("usuario-senha") String senha) {
        UsuarioDto usuarioDto = usuarioService.autenticarUsuario(email, senha);

        if (!(usuarioDto.papel() == PapelUsuario.GERENTE || usuarioDto.papel() == PapelUsuario.RECEPCIONISTA)) {
            throw new RuntimeException("Operação não permitida para o usuário.");
        }

        List<ReservaSimplesDto> relatorio = relatorioService.gerarRelatorioReservasPorPeriodo(dataInicial, dataFinal);

        return ResponseEntity.ok(relatorio);
    }

    @GetMapping("/ocupacao")
    public ResponseEntity<Page<QuartoOcupacaoDto>> obterRelatorioOcupacao(
            @RequestParam(required = false) Integer idCategoriaQuarto,
            @RequestParam StatusRelatorioOcupacao status,
            @PageableDefault(
                    size = 10,
                    sort = {"numBloco", "numAndar"},
                    direction = Sort.Direction.ASC
            ) Pageable pageable,
            @RequestHeader("usuario-email") String email,
            @RequestHeader("usuario-senha") String senha
    ) {
        UsuarioDto usuarioDto = usuarioService.autenticarUsuario(email, senha);

        if (!(usuarioDto.papel() == PapelUsuario.GERENTE)) {
            throw new RuntimeException("Operação não permitida para o usuário.");
        }

        Page<QuartoOcupacaoDto> relatorio = relatorioService.gerarRelatorioOcupacao(idCategoriaQuarto, status, pageable);
        return ResponseEntity.ok(relatorio);
    }

    @GetMapping("/cancelamentos-multa")
    public ResponseEntity<Page<CancelamentoComMultaDto>> obterRelatorioMultas(
            @RequestParam("data-inicial") LocalDate dataInicial,
            @RequestParam("data-final") LocalDate dataFinal,
            @PageableDefault(sort = "valorMulta", direction = Sort.Direction.DESC) Pageable pageable,
            @RequestHeader("usuario-email") String email,
            @RequestHeader("usuario-senha") String senha
    ) {
        UsuarioDto usuarioDto = usuarioService.autenticarUsuario(email, senha);

        if (!(usuarioDto.papel() == PapelUsuario.GERENTE)) {
            throw new RuntimeException("Operação não permitida para o usuário.");
        }

        Page<CancelamentoComMultaDto> relatorio = relatorioService.gerarRelatorioMultas(dataInicial, dataFinal, pageable);
        return ResponseEntity.ok(relatorio);
    }
}
