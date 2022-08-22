package com.vr.miniautorizador.service;

import com.vr.miniautorizador.dto.CartaoDTO;
import com.vr.miniautorizador.dto.TransacaoDTO;
import com.vr.miniautorizador.model.Cartao;
import com.vr.miniautorizador.repository.CartaoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MiniAutorizadorServiceTest {

    @InjectMocks
    MiniAutorizadorService service;

    @Mock
    CartaoRepository repository;

    @Test
    public void testCriarNovoCartaoComSucesso() {
        when(repository.findCartaoByNumero(anyString())).thenReturn(null);

        ResponseEntity<CartaoDTO> response =  service.criarNovoCartao(generateCartaoDto());

        assertEquals(response.getStatusCode().value(), 201);
    }

    @Test
    public void testVerificarSaldoCartaoRecemCriado() {
        Cartao cartaoMock = generateCartaoModel();

        when(repository.findCartaoByNumero(anyString())).thenReturn(cartaoMock);

        ResponseEntity<BigDecimal> response =  service.obterSaldoCartao(cartaoMock.getNumero());

        assertEquals(response.getBody(), BigDecimal.valueOf(500.00));
    }

    private Cartao generateCartaoModel() {
        return new Cartao("9628463957589398", "1234");
    }

    private CartaoDTO generateCartaoDto() {
        CartaoDTO cartaoDto = new CartaoDTO();
        cartaoDto.setNumeroCartao("9628463957589398");
        cartaoDto.setSenhaCartao("1234");
        return cartaoDto;
    }

    private TransacaoDTO generateTransacaoDto() {
        TransacaoDTO transacaoDto = new TransacaoDTO();
        transacaoDto.setNumeroCartao("9628463957589398");
        transacaoDto.setSenha("1234");
        transacaoDto.setValorTransacao(BigDecimal.valueOf(10.00));
        return transacaoDto;
    }
}
