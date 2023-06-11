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
        var valorDaCartela = new BigDecimal("10.00");

        var numerosDaCartela = gerarNumerosCartela(quantidadeDeNumeros);

        var numeroSerie = UUID.randomUUID().toString();
        var cartela = new Cartela();
        cartela.setNumeroSerie(numeroSerie);
        cartela.setUsuario(usuario);
        cartela.setValor(valorDaCartela);
        cartela.setNumeros(numerosDaCartela);

        usuarioRepository.save(usuario);
        cartelaRepository.save(cartela);

        var cartelaDTO = new CartelaDTO();
        cartelaDTO.setId(cartela.getId());
        cartelaDTO.setNumeroSerie(cartela.getNumeroSerie());
        cartelaDTO.setNumeros(cartela.getNumeros());

        return cartelaDTO;
    }
    private List<Integer> gerarNumerosCartela(int quantidade) {
        List<Integer> numerosCartela = new ArrayList<>();
        Set<Integer> numerosExistentes = new HashSet<>();
        var random = new Random();
        while (numerosCartela.size() < quantidade) {
            int numero = random.nextInt(10) + 1;

            if (numerosExistentes.add(numero)) {
                numerosCartela.add(numero);
            }
        }

        return numerosCartela;
    }
}
