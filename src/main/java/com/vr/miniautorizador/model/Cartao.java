package com.vr.miniautorizador.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "miniautorizador", name = "CARTAO")
public class Cartao {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name="numero")
    private String numero;

    @Column(name="senha")
    private String senha;

    @Column(name="saldo")
    private double saldo;

    public Cartao(String numero, String senha) {
        this.numero = numero;
        this.senha = senha;
        this.saldo = 500.00;
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
    
}
