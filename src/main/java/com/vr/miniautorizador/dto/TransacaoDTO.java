package com.vr.miniautorizador.dto;

public class TransacaoDTO {

    private String numeroCartao;
    private String senha;
    private double valorTransacao;

    public TransacaoDTO(String numeroCartao, String senha, double valorTransacao) {
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

    public double getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(double valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

}
