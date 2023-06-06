//package com.rodrigo.bingo.service;
//
//import com.rodrigo.bingo.domain.Cartela;
//import com.rodrigo.bingo.domain.NumeroSorteado;
//import com.rodrigo.bingo.domain.Sorteio;
//import com.rodrigo.bingo.domain.dto.CartelaVencedoraResponse;
//import com.rodrigo.bingo.repository.CartelaRepository;
//import com.rodrigo.bingo.repository.NumeroSorteadoRepository;
//import com.rodrigo.bingo.repository.SorteioRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.*;
//
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import java.time.LocalDateTime;
//
//public class SorteioServiceTest {
//
//    @Mock
//    private SorteioRepository sorteioRepository;
//
//    @Mock
//    private NumeroSorteadoRepository numeroSorteadoRepository;
//
//    @Mock
//    private CartelaRepository cartelaRepository;
//
//    public SorteioServiceTest() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testVerificarCartelaVencedora() {
//        // Números sorteados
//        List<Integer> numerosSorteados = Arrays.asList(5, 2, 8, 9, 1, 4, 7, 6, 10, 3);
//
//        // Cartelas
//        List<List<Integer>> cartelas = Arrays.asList(
//                Arrays.asList(2, 3, 4, 6, 7),
//                Arrays.asList(9, 10, 5, 2, 3),
//                Arrays.asList(5, 6, 7, 9, 10)
//        );
//
//        // Mocking repositories
//        Mockito.when(sorteioRepository.save(Mockito.any(Sorteio.class))).thenReturn(null);
//        Mockito.when(numeroSorteadoRepository.saveAll(Mockito.anyList())).thenReturn(null);
//        Mockito.when(cartelaRepository.saveAll(Mockito.anyList())).thenReturn(null);
//
//        // Instantiate SorteioService?
//
//        // Create Sorteio entity
//        Sorteio sorteio = new Sorteio();
//        sorteio.setDataHora(LocalDateTime.now());
//        Set<NumeroSorteado> numerosSorteadosEntity = new HashSet<>();
//        for (Integer numero : numerosSorteados) {
//            NumeroSorteado numeroSorteado = new NumeroSorteado();
//            numeroSorteado.setNumero(numero);
//            numeroSorteado.setSorteio(sorteio);
//            numerosSorteadosEntity.add(numeroSorteado);
//        }
//        sorteio.setNumerosSorteados(numerosSorteadosEntity);
//
//        // Separação das cartelas
//        List<Cartela> cartelasEntity = new ArrayList<>();
//        Cartela cartela1 = new Cartela();
//        cartela1.setNumeros(cartelas.get(0));
//        cartelasEntity.add(cartela1);
//
//        Cartela cartela2 = new Cartela();
//        cartela2.setNumeros(cartelas.get(1));
//        cartelasEntity.add(cartela2);
//
//        Cartela cartela3 = new Cartela();
//        cartela3.setNumeros(cartelas.get(2));
//        cartelasEntity.add(cartela3);
//
//        Mockito.when(cartelaRepository.findAll()).thenReturn(cartelasEntity);
//
//        // Verificar as cartelas vencedoras
////        List<CartelaVencedoraResponse> cartelasVencedoras = sorteioService.verificarCartelasSorteadas(sorteio);
//
//        // Verificar se a Cartela 3 está na lista de cartelas vencedoras
//        boolean cartela3Vencedora = cartelasVencedoras.stream()
//                .map(CartelaVencedoraResponse::getCartela)
//                .anyMatch(cartela -> cartela.equals(cartela3));
//
//        // Verificar se a Cartela 3 está presente na lista de cartelas vencedoras
//        Assertions.assertTrue(cartela3Vencedora);
//
//        // Verificar se a lista de cartelas vencedoras tem tamanho 1
//        Assertions.assertEquals(1, cartelasVencedoras.size());
//
//        // Verificar se a cartela vencedora contém os números sorteados corretos
//        CartelaVencedoraResponse cartelaVencedora = cartelasVencedoras.get(0);
//        List<Integer> numerosSorteadosVencedora = cartelaVencedora.getNumerosSorteados();
//        Assertions.assertEquals(numerosSorteados, numerosSorteadosVencedora);
//    }
//}
//
//
//
//
//
