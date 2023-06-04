package com.rodrigo.bingo.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "sorteio")
public class Sorteio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @OneToMany(mappedBy = "sorteio", cascade = CascadeType.ALL)
    private Set<NumeroSorteado> numerosSorteados = new HashSet<>();
}
