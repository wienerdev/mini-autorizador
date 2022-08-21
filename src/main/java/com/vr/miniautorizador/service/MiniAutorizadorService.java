package com.vr.miniautorizador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vr.miniautorizador.dto.CartaoDTO;
import com.vr.miniautorizador.model.Cartao;
import com.vr.miniautorizador.repository.CartaoRepository;

@Service
public class MiniAutorizadorService {

    @Autowired
    CartaoRepository repository;
    
    public CartaoDTO criarNovoCartao(CartaoDTO dto) {
        Cartao cartao = parseCartao(dto);
        repository.criarCartao(cartao.getNumero(), cartao.getSenha(), cartao.getSaldo());
        return parseCartaoDto(cartao);
    }

    public double obterSaldoCartao(String numeroCartao) {
        Cartao cartao = repository.getCartaoPorNumero(numeroCartao);
        return cartao.getSaldo();
    }

    public String autorizarTransacao(String numeroCartao, String senha, double valorTransacao) {
        if (isCartaoInexiste(numeroCartao)) {
            return "CARTAO_INEXISTENTE";
        }

        Cartao cartao = repository.getCartaoPorNumero(numeroCartao);
        
        if (isSenhaInvalida(senha, cartao)) {
            return "SENHA_INVALIDA";
        }

        if (isSaldoInsuficiente(valorTransacao, cartao)) {
            return "SALDO_INSUFICIENTE";
        }

        cartao.setSaldo(cartao.getSaldo()-valorTransacao);
        repository.atualizarSaldoPorNumeroCartao(cartao.getSaldo(), cartao.getNumero());

        return "A transação foi efetuada com sucesso!";
    }

    public boolean isCartaoInexiste(String numeroCartao) {
        Integer qntCartao = repository.getQuantidadeCartao(numeroCartao);
        return qntCartao == 0 ? true : false;
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
