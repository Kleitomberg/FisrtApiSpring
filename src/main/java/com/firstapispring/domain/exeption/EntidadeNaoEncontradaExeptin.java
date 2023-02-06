package com.firstapispring.domain.exeption;


public class EntidadeNaoEncontradaExeptin extends NegocioException {

    private static final long serialVersionUID = 1L;

    public EntidadeNaoEncontradaExeptin(String message) {
        super(message);
    }
    
}
    

