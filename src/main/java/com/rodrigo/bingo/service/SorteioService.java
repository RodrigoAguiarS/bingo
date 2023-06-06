package com.rodrigo.bingo.service;

import com.rodrigo.bingo.arq.util.ValidatorUtil;
import com.rodrigo.bingo.domain.Cartela;
import com.rodrigo.bingo.domain.CartelaVencedora;
import com.rodrigo.bingo.domain.NumeroSorteado;
import com.rodrigo.bingo.domain.Sorteio;
import com.rodrigo.bingo.domain.dto.CartelaVencedoraResponse;
import com.rodrigo.bingo.repository.CartelaRepository;
import com.rodrigo.bingo.repository.CartelaVencedoraRepository;
import com.rodrigo.bingo.repository.NumeroSorteadoRepository;
import com.rodrigo.bingo.repository.SorteioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SorteioService {
    private final SorteioRepository sorteioRepository;
    private final NumeroSorteadoRepository numeroSorteadoRepository;
    private final CartelaRepository cartelaRepository;
    private final CartelaVencedoraRepository cartelaVencedoraRepository;


    public Sorteio findById(Long id) {
        return sorteioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Sorteio não encontrado"));
    }

    private BigDecimal calcularValorTotalCartelas() {
        List<Cartela> cartelas = cartelaRepository.findAll();
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (Cartela cartela : cartelas) {
            valorTotal = valorTotal.add(cartela.getValor());
        }

        return valorTotal;
    }


    public Sorteio realizarSorteio() {
        LocalDateTime dataHoraSorteio = LocalDateTime.now();
        Sorteio sorteio = new Sorteio();
        sorteio.setDataHora(dataHoraSorteio);

        BigDecimal valorTotalCartelas = calcularValorTotalCartelas();
        BigDecimal valorPremio = valorTotalCartelas.divide(new BigDecimal(2));
        sorteio.setValorPremio(valorPremio);

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

            while (numerosSorteadosIndex < numerosSorteados.size() && ValidatorUtil.isNotEmpty(numerosCartela)) {
                int numeroSorteado = numerosSorteados.get(numerosSorteadosIndex).getNumero();

                if (numerosCartela.contains(numeroSorteado)) {
                    numerosCartela.remove(Integer.valueOf(numeroSorteado));
                }
                numerosSorteadosIndex++;
            }

            if (ValidatorUtil.isEmpty(numerosCartela) && numerosSorteadosIndex < numerosSorteados.size()) {
                CartelaVencedoraResponse response = criarCartelaVencedoraResponse(cartela, numerosSorteados, numerosSorteadosIndex);
                cartelasVencedoras.add(response);
            }
        }

        List<CartelaVencedoraResponse> cartelasVerificadasVencedoras = ValidaCartelasVencedoras(cartelasVencedoras);
        salvarCartelasVencedoras(sorteio, cartelasVerificadasVencedoras);

        return cartelasVerificadasVencedoras;
    }

    private CartelaVencedoraResponse criarCartelaVencedoraResponse(Cartela cartela, List<NumeroSorteado> numerosSorteados, int numerosSorteadosIndex) {
        CartelaVencedoraResponse response = new CartelaVencedoraResponse();
        response.setCartela(cartela);

        List<Integer> numerosSorteadosVencedora = obterNumerosSorteadosVencedora(numerosSorteados, numerosSorteadosIndex);
        response.setNumerosSorteados(numerosSorteadosVencedora);

        return response;
    }

    private List<Integer> obterNumerosSorteadosVencedora(List<NumeroSorteado> numerosSorteados, int numerosSorteadosIndex) {
        return numerosSorteados.subList(0, numerosSorteadosIndex)
                .stream()
                .map(NumeroSorteado::getNumero)
                .collect(Collectors.toList());
    }

    private List<CartelaVencedoraResponse> ValidaCartelasVencedoras(List<CartelaVencedoraResponse> cartelasVencedoras) {
        int menorIndex = cartelasVencedoras.stream()
                .mapToInt(cartelaVencedora -> cartelaVencedora.getNumerosSorteados().size())
                .min()
                .orElse(Integer.MAX_VALUE);

        return cartelasVencedoras.stream()
                .filter(cartelaVencedora -> cartelaVencedora.getNumerosSorteados().size() == menorIndex)
                .collect(Collectors.toList());
    }

    public void salvarCartelasVencedoras(Sorteio sorteio, List<CartelaVencedoraResponse> cartelasVencedoras) {
        for (CartelaVencedoraResponse cartelaVencedoraResponse : cartelasVencedoras) {
            CartelaVencedora cartelaVencedora = new CartelaVencedora();
            cartelaVencedora.setSorteio(sorteio);
            cartelaVencedora.setCartela(cartelaVencedoraResponse.getCartela());
            cartelaVencedora.setNumerosSorteados(cartelaVencedoraResponse.getNumerosSorteados());

            cartelaVencedoraRepository.save(cartelaVencedora);
        }
    }
}

