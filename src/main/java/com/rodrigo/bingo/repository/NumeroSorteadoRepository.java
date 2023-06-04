package com.rodrigo.bingo.repository;

import com.rodrigo.bingo.domain.Cartela;
import com.rodrigo.bingo.domain.NumeroSorteado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumeroSorteadoRepository extends JpaRepository<NumeroSorteado, Long> {
}
