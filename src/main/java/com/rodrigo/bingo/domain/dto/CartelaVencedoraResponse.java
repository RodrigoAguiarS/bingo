package com.rodrigo.bingo.domain.dto;

import com.rodrigo.bingo.domain.Cartela;
import lombok.Data;

import java.util.List;

@Data
public class CartelaVencedoraResponse {
    private Cartela cartela;
    private List<Integer> numerosSorteados;
}
