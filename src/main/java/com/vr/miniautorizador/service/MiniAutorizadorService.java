package com.vr.miniautorizador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vr.miniautorizador.dto.CartaoDTO;
import com.vr.miniautorizador.dto.TransacaoDTO;
import com.vr.miniautorizador.exception.MiniAutorizadorException;
import com.vr.miniautorizador.model.Cartao;
import com.vr.miniautorizador.repository.CartaoRepository;

@Service
public class MiniAutorizadorService {

    @Autowired
    CartaoRepository repository;
    
    public ResponseEntity<CartaoDTO> criarNovoCartao(CartaoDTO request) {
        if (isCartaoExistente(request.getNumeroCartao())) {
            return new ResponseEntity<>(new CartaoDTO(request.getNumeroCartao(), request.getSenhaCartao()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Cartao cartao = parseCartao(request);
        repository.criarCartao(cartao.getNumero(), cartao.getSenha(), cartao.getSaldo());

        return new ResponseEntity<>(parseCartaoDto(cartao), HttpStatus.CREATED);
    }

    public ResponseEntity<Double> obterSaldoCartao(String numeroCartao) {
        Cartao cartao = repository.getCartaoPorNumero(numeroCartao);
        return new ResponseEntity<>(cartao.getSaldo(), HttpStatus.OK);
    }

    public ResponseEntity<String> autorizarTransacao(TransacaoDTO request) {
        if (!isCartaoExistente(request.getNumeroCartao())) {
            return new ResponseEntity<>("CARTAO_INEXISTENTE", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Cartao cartao = repository.getCartaoPorNumero(request.getNumeroCartao());
        
        if (isSenhaInvalida(request.getSenha(), cartao.getSenha())) {
            return new ResponseEntity<>("SENHA_INVALIDA", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (isSaldoInsuficiente(request.getValorTransacao(), cartao.getSaldo())) {
            return new ResponseEntity<>("SALDO_INSUFICIENTE", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        cartao.setSaldo(cartao.getSaldo()-request.getValorTransacao());
        repository.save(cartao);
        //repository.atualizarSaldoPorNumeroCartao(cartao.getSaldo(), cartao.getNumero());

        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    private boolean isCartaoExistente(String numeroCartao) {
        Cartao cartao = repository.getCartaoPorNumero(numeroCartao);
        return cartao != null;
    }

    private boolean isSaldoInsuficiente(double valorTransacao, double saldoCartao) {
        return valorTransacao > saldoCartao;
    }

    private boolean isSenhaInvalida(String senha, String senhaCartao) {
        return !senha.equals(senhaCartao);
    }

    private Cartao parseCartao(CartaoDTO dto) {
        return new Cartao(dto.getNumeroCartao(), dto.getSenhaCartao());
    }

    private CartaoDTO parseCartaoDto(Cartao cartao) {
        return new CartaoDTO(cartao.getNumero(), cartao.getSenha());
    }
}
