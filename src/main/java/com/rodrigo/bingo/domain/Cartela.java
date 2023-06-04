package com.rodrigo.bingo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "cartela")
public class Cartela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_serie")
    private String numeroSerie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Usuario usuario;

    @ElementCollection
    @CollectionTable(name = "cartela_numeros", joinColumns = @JoinColumn(name = "cartela_id"))
    @Column(name = "numero")
    private List<Integer> numeros;

}
