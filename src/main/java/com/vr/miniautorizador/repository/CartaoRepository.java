package com.vr.miniautorizador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vr.miniautorizador.model.Cartao;

import java.math.BigDecimal;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

    Cartao findCartaoByNumero(String numero);

    @Query(value = "INSERT INTO CARTAO(numero, senha, saldo) "
    + " VALUES (?1, ?2, ?3) ", nativeQuery = true)
    @Modifying
    @Transactional
    void criarCartao(String numeroCartao, String senha, BigDecimal saldo);

}
