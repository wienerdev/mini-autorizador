package com.vr.miniautorizador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vr.miniautorizador.model.Cartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

    Cartao findCartaoByNumero(String numero);

}
