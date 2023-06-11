package com.rodrigo.bingo.rest;

import com.rodrigo.bingo.arq.util.ValidatorUtil;
import com.rodrigo.bingo.domain.dto.DetalhesSorteioDTO;
import com.rodrigo.bingo.service.SorteioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SorteioController {
    private final SorteioService sorteioService;

    @PostMapping("/sorteio")
    public ResponseEntity<String> realizarSorteio() {
        sorteioService.realizarSorteio();
        return ResponseEntity.ok("Sorteio realizado com sucesso");
    }

    @GetMapping("/{id}/detalhes")
    public ResponseEntity<DetalhesSorteioDTO> detalharSorteio(@PathVariable("id") Long sorteioId) {
        var sorteio = sorteioService.findById(sorteioId);
        if (ValidatorUtil.isEmpty(sorteio)) {
            return ResponseEntity.notFound().build();
        }
        var detalhesSorteio = sorteioService.detalharSorteio(sorteioId);
        return ResponseEntity.ok(detalhesSorteio);
    }
}
