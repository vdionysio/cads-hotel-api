package br.ifs.edu.cads.api.hotel.controller;

import br.ifs.edu.cads.api.hotel.dto.CidadeDto;
import br.ifs.edu.cads.api.hotel.dto.CidadeFormDto;
import br.ifs.edu.cads.api.hotel.service.CidadeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {

    private final CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping
    public ResponseEntity<List<CidadeDto>> getCidadesByUf(@RequestParam(name = "uf") String uf) {
        List<CidadeDto> cidades = cidadeService.findCidadesByUf(uf.toUpperCase());
        return ResponseEntity.ok(cidades);
    }

    @PostMapping
    public ResponseEntity<CidadeDto> createCidade(@RequestBody @Valid CidadeFormDto cidadeFormDto) {
        CidadeDto cidadeDto = cidadeService.createCidade(cidadeFormDto);
        URI location = URI.create("/api/cidades/" + cidadeDto.id());
        return ResponseEntity.created(location).body(cidadeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCidade(@RequestBody @Valid CidadeFormDto cidadeFormDto, @PathVariable Long id) {
        cidadeService.updateCidade(cidadeFormDto, id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CidadeDto> getCidadeById(@PathVariable Long id) {
        CidadeDto cidadeDto = cidadeService.findById(id);

        return ResponseEntity.ok(cidadeDto);
    }
}
