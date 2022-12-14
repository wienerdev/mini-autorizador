package com.vr.miniautorizador.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "MINI_AUTORIZADOR", name = "CARTAO")
public class Cartao {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="numero")
    private String numero;

    @Column(name="senha")
    private String senha;

    @Column(name="saldo")
    private BigDecimal saldo;

    public Cartao() {
    }

    public Cartao(String numero, String senha) {
        this.numero = numero;
        this.senha = senha;
        this.saldo = BigDecimal.valueOf(500.00);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cartao)) {
            return false;
        }
        Cartao other = (Cartao) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Informações do cartão: [numero=" + numero + ", saldo=" + saldo + ", senha=" + senha + "]";
    }

}
