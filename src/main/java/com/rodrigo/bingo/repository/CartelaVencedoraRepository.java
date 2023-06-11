package com.rodrigo.bingo.repository;

import com.rodrigo.bingo.domain.Cartela;
import com.rodrigo.bingo.domain.CartelaVencedora;
import com.rodrigo.bingo.domain.Sorteio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartelaVencedoraRepository extends JpaRepository<CartelaVencedora, Long> {

    @Query("SELECT cv.cartela FROM CartelaVencedora cv WHERE cv.sorteio = :sorteio")
    List<Cartela> findCartelasPremiadas(@Param("sorteio") Sorteio sorteio);
}
