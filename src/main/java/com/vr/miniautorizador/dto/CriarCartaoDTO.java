package com.vr.miniautorizador.dto;

import java.util.Objects;

import com.vr.miniautorizador.model.Cartao;

public class CriarCartaoDTO {

    private String numeroCartao;
    private String senhaCartao;
    private double saldo;
    
    @Override
    public int hashCode() {
        return Objects.hash(numeroCartao);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cartao)) {
            return false;
        }
        CriarCartaoDTO other = (CriarCartaoDTO) obj;
        return Objects.equals(numeroCartao, other.numeroCartao);
    }

    @Override
    public String toString() {
        return "Informações do cartão criado: [numeroCartao=" + numeroCartao + ", saldo=" + saldo + "]";
    }
    
}
