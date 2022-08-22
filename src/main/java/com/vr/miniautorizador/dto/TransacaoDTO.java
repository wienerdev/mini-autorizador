package com.vr.miniautorizador.dto;

import java.math.BigDecimal;

public class TransacaoDTO {

    private String numeroCartao;
    private String senha;
    private BigDecimal valorTransacao;

    public TransacaoDTO() {
    }

    public TransacaoDTO(String numeroCartao, String senha, BigDecimal valorTransacao) {
        this.numeroCartao = numeroCartao;
        this.senha = senha;
        this.valorTransacao = valorTransacao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public BigDecimal getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(BigDecimal valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

}
