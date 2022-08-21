package com.vr.miniautorizador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.vr.miniautorizador.dto.CartaoDTO;
import com.vr.miniautorizador.service.ConsultarSaldoService;
import com.vr.miniautorizador.service.CriarCartaoService;

@RestController
@RequestMapping(value = "cartoes")
public class MiniAutorizadorController {

    @Autowired
    CriarCartaoService criarCartaoService;

    @Autowired
    ConsultarSaldoService consultarSaldoService;

    @GetMapping("/obter-saldo/{numeroCartao}")
    public @ResponseBody ResponseEntity<Double> obterSaldoCartao(@PathVariable String numeroCartao) {
        return new ResponseEntity<Double>(consultarSaldoService.obterSaldoCartao(numeroCartao), HttpStatus.OK);
    }

    @PostMapping("/criar-cartao")
    public @ResponseBody ResponseEntity<CartaoDTO> criarNovoCartao(@RequestBody CartaoDTO cartaoDto) {
        if (criarCartaoService.isCartaoExiste(cartaoDto.getNumeroCartao())) {
            return new ResponseEntity<CartaoDTO>(new CartaoDTO(cartaoDto.getNumeroCartao(), cartaoDto.getSenhaCartao()), HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            return new ResponseEntity<CartaoDTO>(criarCartaoService.criarNovoCartao(cartaoDto), HttpStatus.CREATED);
        }
    }
    
}
