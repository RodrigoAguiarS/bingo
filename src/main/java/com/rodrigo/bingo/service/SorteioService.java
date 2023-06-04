package com.rodrigo.bingo.service;

import com.rodrigo.bingo.domain.Cartela;
import com.rodrigo.bingo.domain.NumeroSorteado;
import com.rodrigo.bingo.domain.Sorteio;
import com.rodrigo.bingo.domain.dto.CartelaVencedoraResponse;
import com.rodrigo.bingo.repository.CartelaRepository;
import com.rodrigo.bingo.repository.NumeroSorteadoRepository;
import com.rodrigo.bingo.repository.SorteioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SorteioService {
    private final SorteioRepository sorteioRepository;
    private final NumeroSorteadoRepository numeroSorteadoRepository;
    private final CartelaRepository cartelaRepository;


    public Sorteio findById(Long id) {
        return sorteioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Sorteio não encontrado"));
    }

    public Sorteio realizarSorteio() {
        LocalDateTime dataHoraSorteio = LocalDateTime.now();
        Sorteio sorteio = new Sorteio();
        sorteio.setDataHora(dataHoraSorteio);

        sorteio = sorteioRepository.save(sorteio);

        List<Integer> numerosSorteados = gerarNumerosSorteados();

        for (Integer numero : numerosSorteados) {
            NumeroSorteado numeroSorteado = new NumeroSorteado();
            numeroSorteado.setNumero(numero);
            numeroSorteado.setSorteio(sorteio);
            numeroSorteadoRepository.save(numeroSorteado);
        }

        return sorteio;
    }

    private List<Integer> gerarNumerosSorteados() {
        List<Integer> numerosSorteados = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            numerosSorteados.add(i);
        }
        Collections.shuffle(numerosSorteados);
        return numerosSorteados;
    }

    public List<CartelaVencedoraResponse> verificarCartelasSorteadas(Sorteio sorteio) {
        List<Cartela> cartelas = cartelaRepository.findAll();
        List<NumeroSorteado> numerosSorteados = new ArrayList<>(sorteio.getNumerosSorteados());

        List<CartelaVencedoraResponse> cartelasVencedoras = new ArrayList<>();

        for (Cartela cartela : cartelas) {
            List<Integer> numerosCartela = new ArrayList<>(cartela.getNumeros());
            int numerosSorteadosIndex = 0;

            while (numerosSorteadosIndex < numerosSorteados.size() && !numerosCartela.isEmpty()) {
                int numeroSorteado = numerosSorteados.get(numerosSorteadosIndex).getNumero();

                if (numerosCartela.contains(numeroSorteado)) {
                    numerosCartela.remove(Integer.valueOf(numeroSorteado));
                }
                numerosSorteadosIndex++;
            }

            if (numerosCartela.isEmpty() && numerosSorteadosIndex < numerosSorteados.size()) {
                CartelaVencedoraResponse response = new CartelaVencedoraResponse();
                response.setCartela(cartela);
                List<Integer> numerosSorteadosVencedora = numerosSorteados.subList(0, numerosSorteadosIndex).stream()
                        .map(NumeroSorteado::getNumero)
                        .collect(Collectors.toList());
                response.setNumerosSorteados(numerosSorteadosVencedora);
                cartelasVencedoras.add(response);
            }
        }

        List<CartelaVencedoraResponse> cartelasVerificadasVencedoras = new ArrayList<>();
        int menorIndex = Integer.MAX_VALUE;

        for (CartelaVencedoraResponse cartelaVencedora : cartelasVencedoras) {
            int numerosSorteadosIndex = cartelaVencedora.getNumerosSorteados().size();
            if (numerosSorteadosIndex < menorIndex) {
                menorIndex = numerosSorteadosIndex;
                cartelasVerificadasVencedoras.clear();
                cartelasVerificadasVencedoras.add(cartelaVencedora);
            } else if (numerosSorteadosIndex == menorIndex) {
                cartelasVerificadasVencedoras.add(cartelaVencedora);
            }
        }

        return cartelasVerificadasVencedoras;
    }
}


