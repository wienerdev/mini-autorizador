package com.vr.miniautorizador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vr.miniautorizador.dto.CartaoDTO;
import com.vr.miniautorizador.dto.TransacaoDTO;
import com.vr.miniautorizador.model.Cartao;
import com.vr.miniautorizador.repository.CartaoRepository;

import java.math.BigDecimal;

@Service
public class MiniAutorizadorService {

    @Autowired
    CartaoRepository repository;

    public ResponseEntity<CartaoDTO> criarNovoCartao(CartaoDTO request) {
        if (isCartaoExistente(request.getNumeroCartao())) {
            return creationErrorResponse(request, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if(isTamanhoNumeroCartaoInvalido(request)) {
            return creationErrorResponse(request, HttpStatus.BAD_REQUEST);
        }

        Cartao cartao = parseCartao(request);

        // Demonstracao utilizando query nativa, poderia ter sido utilizado também o método do JPA (repository.save(cartao))
        try {
            repository.criarCartao(cartao.getNumero(), cartao.getSenha(), cartao.getSaldo());
        } catch (Exception e) {
            e.printStackTrace();
            return creationErrorResponse(request, HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<>(parseCartaoDto(cartao), HttpStatus.CREATED);
    }

    public ResponseEntity<BigDecimal> obterSaldoCartao(String numeroCartao) {
        Cartao cartao = repository.findCartaoByNumero(numeroCartao);

        if (cartao == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(cartao.getSaldo(), HttpStatus.OK);
    }

    public ResponseEntity<String> autorizarTransacao(TransacaoDTO request) {
        Cartao cartao = repository.findCartaoByNumero(request.getNumeroCartao());

        if (cartao == null) {
            return new ResponseEntity<>("CARTAO_INEXISTENTE", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        
        if (isSenhaInvalida(request.getSenha(), cartao.getSenha())) {
            return new ResponseEntity<>("SENHA_INVALIDA", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (isSaldoInsuficiente(request.getValorTransacao(), cartao.getSaldo())) {
            return new ResponseEntity<>("SALDO_INSUFICIENTE", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        cartao.setSaldo(cartao.getSaldo().subtract(request.getValorTransacao()));
        repository.save(cartao);

        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    private boolean isCartaoExistente(String numeroCartao) {
        Cartao cartao = repository.findCartaoByNumero(numeroCartao);
        return cartao != null;
    }

    private static ResponseEntity<CartaoDTO> creationErrorResponse(CartaoDTO request, HttpStatus status) {
        return new ResponseEntity<>(new CartaoDTO(request.getNumeroCartao(), request.getSenhaCartao()), status);
    }

    private static boolean isTamanhoNumeroCartaoInvalido(CartaoDTO request) {
        return request.getNumeroCartao().length() != 16;
    }

    private Cartao parseCartao(CartaoDTO dto) {
        return new Cartao(dto.getNumeroCartao(), dto.getSenhaCartao());
    }

    private boolean isSaldoInsuficiente(BigDecimal valorTransacao, BigDecimal saldoCartao) {
        return valorTransacao.compareTo(saldoCartao) == 1;
    }

    private boolean isSenhaInvalida(String senha, String senhaCartao) {
        return !senha.equals(senhaCartao);
    }

    private CartaoDTO parseCartaoDto(Cartao cartao) {
        return new CartaoDTO(cartao.getNumero(), cartao.getSenha());
    }
}
