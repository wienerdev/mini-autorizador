package com.vr.miniautorizador.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vr.miniautorizador.model.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

    @Query(value = "SELECT * FROM CARTAO ", nativeQuery=true)
    public Collection<Cartao> getAllCartoes();

    @Query(value = "SELECT COUNT(*) FROM CARTAO "
     + " WHERE numero = ?1 ", nativeQuery = true)
    public Integer isCartaoExiste(String numeroCartao);

    @Query(value = "INSERT INTO CARTAO(numero, senha, saldo) "
    + " VALUES (?1, ?2, ?3) ", nativeQuery = true)
    @Modifying
    @Transactional
    public void criarCartao(String numeroCartao, String senha, double saldo);

    @Query(value = "SELECT c.saldo FROM CARTAO as c "
    + " WHERE c.numero = ?1 ", nativeQuery = true)
    public double getSaldoCartao(String numeroCartao);
    
}
