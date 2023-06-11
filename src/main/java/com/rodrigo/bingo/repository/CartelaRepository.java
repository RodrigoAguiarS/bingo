package com.rodrigo.bingo.repository;

import com.rodrigo.bingo.domain.Cartela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartelaRepository extends JpaRepository<Cartela, Long> {

    @Query("SELECT c FROM Cartela c WHERE c.usada = false")
    List<Cartela> findByUsadaFalse();
}
