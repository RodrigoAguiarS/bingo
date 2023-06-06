package com.rodrigo.bingo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "cartela_vencedora")
public class CartelaVencedora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartela_vencedora")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_sorteio")
    private Sorteio sorteio;
    @ManyToOne
    @JoinColumn(name = "id_cartela")
    private Cartela cartela;
    @ElementCollection
    @CollectionTable(name = "numeros_premiados",
            joinColumns = @JoinColumn(name = "id_cartela_vencedora"))
    @Column(name = "numero")
    private List<Integer> numerosSorteados;

}

