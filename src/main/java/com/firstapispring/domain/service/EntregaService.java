package com.firstapispring.domain.service;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.firstapispring.domain.model.Entrega;
import com.firstapispring.domain.model.StatusEntrega;
import com.firstapispring.domain.repository.EntregaRepository;
import com.firstapispring.domain.repository.ClienteRepository;
import com.firstapispring.domain.exeption.NegocioException;

import com.firstapispring.domain.model.Cliente;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EntregaService {

    private EntregaRepository entregaRepository;
    private ClienteService clienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega) {

        Cliente cliente = clienteService.buscar(entrega.getCliente().getId());      
                
        
        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(LocalDateTime.now());

        return entregaRepository.save(entrega);
    }

    @Transactional
    public ResponseEntity<Entrega> buscar(Long id){
        Entrega entrega = entregaRepository.findById(id).orElseThrow(() -> new NegocioException("Entrega não encontrada"));
        return ResponseEntity.ok(entrega);

    }
    
}
