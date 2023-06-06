package com.rodrigo.bingo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartelaDTO {

    private Long id;
    private String numeroSerie;
    private List<Integer> numeros;
}
