package com.rodrigo.bingo.service;


import com.rodrigo.bingo.domain.Cartela;
import com.rodrigo.bingo.domain.Usuario;
import com.rodrigo.bingo.domain.dto.CartelaDTO;
import com.rodrigo.bingo.repository.CartelaRepository;
import com.rodrigo.bingo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
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

    private final UsuarioRepository usuarioRepository;

    public CartelaDTO gerarNovaCartela(Usuario usuario) {

        int quantidadeDeNumeros = 5;
        BigDecimal valorDaCartela = new BigDecimal("10.00");

        List<Integer> numerosDaCartela = gerarNumerosCartela(quantidadeDeNumeros);

        String numeroSerie = UUID.randomUUID().toString();
        Cartela cartela = new Cartela();
        cartela.setNumeroSerie(numeroSerie);
        cartela.setUsuario(usuario);
        cartela.setValor(valorDaCartela);
        cartela.setNumeros(numerosDaCartela);

        usuarioRepository.save(usuario);
        cartelaRepository.save(cartela);

        CartelaDTO cartelaDTO = new CartelaDTO();
        cartelaDTO.setId(cartela.getId());
        cartelaDTO.setNumeroSerie(cartela.getNumeroSerie());
        cartelaDTO.setNumeros(cartela.getNumeros());

        return cartelaDTO;
    }
    private List<Integer> gerarNumerosCartela(int quantidade) {
        List<Integer> numerosCartela = new ArrayList<>();
        Set<Integer> numerosExistentes = new HashSet<>();
        Random random = new Random();
        while (numerosCartela.size() < quantidade) {
            int numero = random.nextInt(10) + 1;

            if (numerosExistentes.add(numero)) {
                numerosCartela.add(numero);
            }
        }

        return numerosCartela;
    }
}
