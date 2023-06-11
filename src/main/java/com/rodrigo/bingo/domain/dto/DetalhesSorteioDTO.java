package com.rodrigo.bingo.domain.dto;

import com.rodrigo.bingo.domain.Sorteio;
import lombok.Data;

import java.util.List;
@Data
public class DetalhesSorteioDTO {
    private Sorteio sorteio;
    private List<Integer> numerosPremiados;
    private List<CartelaVencedoraResponse> cartelasPremiadas;

}
