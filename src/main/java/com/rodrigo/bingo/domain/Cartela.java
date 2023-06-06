package com.rodrigo.bingo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Data
@Entity
@Table(name = "cartela")
public class Cartela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartela")
    private Long id;

    @Column(name = "numero_serie")
    private String numeroSerie;
    @Column(name = "valor")
    private BigDecimal valor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Usuario usuario;

    @ElementCollection
    @CollectionTable(name = "cartela_numeros", joinColumns = @JoinColumn(name = "id_cartela"))
    @Column(name = "numero")
    private List<Integer> numeros;

}
