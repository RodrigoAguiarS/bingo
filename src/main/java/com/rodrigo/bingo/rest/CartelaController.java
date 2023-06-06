package com.rodrigo.bingo.rest;

import com.rodrigo.bingo.arq.util.ValidatorUtil;
import com.rodrigo.bingo.domain.Cartela;
import com.rodrigo.bingo.domain.Sorteio;
import com.rodrigo.bingo.domain.Usuario;
import com.rodrigo.bingo.domain.dto.CartelaDTO;
import com.rodrigo.bingo.domain.dto.CartelaVencedoraResponse;
import com.rodrigo.bingo.service.CartelaService;
import com.rodrigo.bingo.service.SorteioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/cartelas")
@RequiredArgsConstructor
public class CartelaController {
    private final CartelaService cartelaService;
    private final SorteioService sorteioService;

    @PostMapping
    public ResponseEntity<CartelaDTO> gerarNovaCartela(@RequestBody Usuario usuario) {
        CartelaDTO cartela = cartelaService.gerarNovaCartela(usuario);
        return ResponseEntity.ok(cartela);
    }
    @GetMapping("/verificar-cartelas-sorteadas/{sorteioId}")
    public ResponseEntity<?> verificarCartelasSorteadas(@PathVariable Long sorteioId) {
        Sorteio sorteio = sorteioService.findById(sorteioId);
        List<CartelaVencedoraResponse> cartelasVencedoras = sorteioService.verificarCartelasSorteadas(sorteio);

        if (ValidatorUtil.isEmpty(cartelasVencedoras)) {
            return ResponseEntity.ok().body("Nenhuma cartela foi premiada");
        }
        return ResponseEntity.ok(cartelasVencedoras);
    }
}

