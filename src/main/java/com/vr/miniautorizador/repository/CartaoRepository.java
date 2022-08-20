package com.vr.miniautorizador.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vr.miniautorizador.model.Cartao;

@Repository
public interface CartaoRepository extends CrudRepository<Cartao, Integer> {
    
}
