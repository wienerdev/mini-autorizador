package com.vr.miniautorizador.dto;

public class CartaoDTO {

    private String numeroCartao;
    private String senhaCartao;

    public CartaoDTO() {
    }

    public CartaoDTO(String numeroCartao, String senhaCartao) {
        this.numeroCartao = numeroCartao;
        this.senhaCartao = senhaCartao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getSenhaCartao() {
        return senhaCartao;
    }

    public void setSenhaCartao(String senhaCartao) {
        this.senhaCartao = senhaCartao;
    }
    
}
