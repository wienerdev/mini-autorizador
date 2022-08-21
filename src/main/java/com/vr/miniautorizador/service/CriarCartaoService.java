package com.vr.miniautorizador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vr.miniautorizador.dto.CartaoDTO;
import com.vr.miniautorizador.model.Cartao;
import com.vr.miniautorizador.repository.CartaoRepository;

@Service
public class CriarCartaoService {

    @Autowired
    CartaoRepository repository;

    public CartaoDTO criarNovoCartao(CartaoDTO dto) {
        Cartao cartao = parseCartao(dto);
        repository.criarCartao(cartao.getNumero(), cartao.getSenha(), cartao.getSaldo());
        return parseCartaoDto(cartao);
    }

    public boolean isCartaoExiste(String numeroCartao) {
        Integer val = repository.isCartaoExiste(numeroCartao);
        if (val == 0) {
            return false;
        } else {
            return true;
        }
    }

    private Cartao parseCartao(CartaoDTO dto) {
        return new Cartao(dto.getNumeroCartao(), dto.getSenhaCartao());
    }

    private CartaoDTO parseCartaoDto(Cartao cartao) {
        return new CartaoDTO(cartao.getNumero(), cartao.getSenha());
    }
    
}
