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
import com.vr.miniautorizador.dto.TransacaoDTO;
import com.vr.miniautorizador.service.MiniAutorizadorService;

@RestController
@RequestMapping(value = "cartoes")
public class MiniAutorizadorController {

    @Autowired
    MiniAutorizadorService service;

    @GetMapping("/obter-saldo/{numeroCartao}")
    public @ResponseBody ResponseEntity<Double> obterSaldoCartao(@PathVariable String numeroCartao) {
        return new ResponseEntity<Double>(service.obterSaldoCartao(numeroCartao), HttpStatus.OK);
    }

    @PostMapping("/criar-cartao")
    public @ResponseBody ResponseEntity<CartaoDTO> criarNovoCartao(@RequestBody CartaoDTO cartaoDto) {
        if (service.isCartaoInexiste(cartaoDto.getNumeroCartao())) {
            return new ResponseEntity<CartaoDTO>(service.criarNovoCartao(cartaoDto), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<CartaoDTO>(new CartaoDTO(cartaoDto.getNumeroCartao(), cartaoDto.getSenhaCartao()), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PostMapping("transacoes")
    public @ResponseBody ResponseEntity<String> realizarTransacao(@RequestBody TransacaoDTO transacaoDTO) {
        return new ResponseEntity<String>(
            service.autorizarTransacao(
                transacaoDTO.getNumeroCartao(),
                transacaoDTO.getSenha(),
                transacaoDTO.getValorTransacao()
            ),
            HttpStatus.CREATED
        );
    }
    
}
