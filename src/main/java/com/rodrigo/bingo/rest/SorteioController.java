package com.rodrigo.bingo.rest;

import com.rodrigo.bingo.service.SorteioService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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
}
