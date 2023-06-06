package com.rodrigo.bingo.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "sorteio")
public class Sorteio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sorteio")
    private Long id;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    @Column(name = "valor_premio")
    private BigDecimal valorPremio;

    @OneToMany(mappedBy = "sorteio", cascade = CascadeType.ALL)
    private Set<NumeroSorteado> numerosSorteados = new HashSet<>();
}
