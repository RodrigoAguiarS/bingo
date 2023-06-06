package com.rodrigo.bingo.repository;

import com.rodrigo.bingo.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
