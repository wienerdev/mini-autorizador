package com.vr.miniautorizador.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vr.miniautorizador.repository.CartaoRepository;

@Service
public class ConsultarSaldoService {

    @Autowired
    CartaoRepository repository;

    public double obterSaldoCartao(String numeroCartao) {
        return repository.getSaldoCartao(numeroCartao);
    }
    
}
