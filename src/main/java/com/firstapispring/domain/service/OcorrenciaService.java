package com.firstapispring.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.firstapispring.domain.model.Ocorrencia;
import com.firstapispring.domain.model.Entrega;
import com.firstapispring.domain.repository.EntregaRepository;
import com.firstapispring.domain.exeption.EntidadeNaoEncontradaExeptin;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class OcorrenciaService {

    private EntregaRepository entregaRepository;

    public Entrega buscarEntrega(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaExeptin("Entrega não encontrada"));
    }
    
    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {

        Entrega entrega = buscarEntrega(entregaId);
        
        return entrega.adicionarOcorrencia(descricao);

    }
}
