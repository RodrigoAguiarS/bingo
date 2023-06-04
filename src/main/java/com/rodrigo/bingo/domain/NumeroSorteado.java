package com.rodrigo.bingo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "numero_sorteado")
public class NumeroSorteado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sorteio_id")
    @EqualsAndHashCode.Exclude
    private Sorteio sorteio;
}
