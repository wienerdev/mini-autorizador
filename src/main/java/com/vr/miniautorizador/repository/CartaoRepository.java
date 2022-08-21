package com.vr.miniautorizador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vr.miniautorizador.model.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

    Cartao findCartaoByNumero(String numero);

    @Query(value = "SELECT * FROM CARTAO as c "
    + "WHERE c.numero = ?1", nativeQuery = true)
    public Cartao getCartaoPorNumero(String numeroCartao);

    @Query(value = "UPDATE CARTAO SET saldo = ?1 "
    + " WHERE numero = ?2 ", nativeQuery = true)
    @Modifying
    @Transactional
    public void atualizarSaldoPorNumeroCartao(double saldo, String numeroCartao);

    @Query(value = "INSERT INTO CARTAO(numero, senha, saldo) "
    + " VALUES (?1, ?2, ?3) ", nativeQuery = true)
    @Modifying
    @Transactional
    public void criarCartao(String numeroCartao, String senha, double saldo);

}
