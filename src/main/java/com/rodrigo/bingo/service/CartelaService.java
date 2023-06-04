package com.rodrigo.bingo.service;

import com.rodrigo.bingo.domain.Cartela;
import com.rodrigo.bingo.domain.Usuario;
import com.rodrigo.bingo.repository.CartelaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartelaService {
    private final CartelaRepository cartelaRepository;
    public Cartela gerarNovaCartela(Usuario usuario) {
        Set<Integer> numerosSorteados = new HashSet<>();
        Random random = new Random();

        while (numerosSorteados.size() < 5) {
            int numero = random.nextInt(10) + 1;
            numerosSorteados.add(numero);
        }

        List<Integer> numerosSorteadosList = new ArrayList<>(numerosSorteados);

        String numeroSerie = UUID.randomUUID().toString();

        Cartela cartela = new Cartela();
        cartela.setNumeroSerie(numeroSerie);
        cartela.setUsuario(usuario);
        cartela.setNumeros(numerosSorteadosList);

        return cartelaRepository.save(cartela);
    }
}

