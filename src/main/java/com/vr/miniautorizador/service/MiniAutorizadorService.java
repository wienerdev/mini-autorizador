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
            return new ResponseEntity<CartaoDTO>(new CartaoDTO(request.getNumeroCartao(), request.getSenhaCartao()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        Cartao cartao = parseCartao(request);
        repository.criarCartao(cartao.getNumero(), cartao.getSenha(), cartao.getSaldo());

        return new ResponseEntity<CartaoDTO>(parseCartaoDto(cartao), HttpStatus.CREATED);
    }

    public ResponseEntity<Double> obterSaldoCartao(String numeroCartao) {
        Cartao cartao = repository.getCartaoPorNumero(numeroCartao);
        return new ResponseEntity<Double>(cartao.getSaldo(), HttpStatus.OK);
    }

    public ResponseEntity<String> autorizarTransacao(TransacaoDTO request) throws MiniAutorizadorException {
        if (!isCartaoExistente(request.getNumeroCartao())) {
            return new ResponseEntity<String>("CARTAO_INEXISTENTE", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        Cartao cartao = repository.getCartaoPorNumero(request.getNumeroCartao());
        
        if (isSenhaInvalida(cartao.getSenha(), cartao)) {
            return new ResponseEntity<String>("SENHA_INVALIDA", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (isSaldoInsuficiente(cartao.getSaldo(), cartao)) {
            return new ResponseEntity<String>("SALDO_INSUFICIENTE", HttpStatus.UNPROCESSABLE_ENTITY);
        }

        cartao.setSaldo(cartao.getSaldo()-request.getValorTransacao());
        repository.atualizarSaldoPorNumeroCartao(cartao.getSaldo(), cartao.getNumero());

        return new ResponseEntity<String>("OK", HttpStatus.CREATED);
    }

    private boolean isCartaoExistente(String numeroCartao) {
        Cartao cartao = repository.getCartaoPorNumero(numeroCartao);
        return cartao != null;
    }

    private boolean isSaldoInsuficiente(double valorTransacao, Cartao cartao) {
        return valorTransacao > cartao.getSaldo();
    }

    private boolean isSenhaInvalida(String senha, Cartao cartao) {
        return !senha.equals(cartao.getSenha());
    }

    private Cartao parseCartao(CartaoDTO dto) {
        return new Cartao(dto.getNumeroCartao(), dto.getSenhaCartao());
    }

    private CartaoDTO parseCartaoDto(Cartao cartao) {
        return new CartaoDTO(cartao.getNumero(), cartao.getSenha());
    }
}
