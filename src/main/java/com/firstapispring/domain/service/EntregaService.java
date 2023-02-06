package com.firstapispring.domain.service;

import java.time.OffsetDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.firstapispring.domain.model.Entrega;
import com.firstapispring.domain.model.StatusEntrega;
import com.firstapispring.domain.repository.EntregaRepository;
import com.firstapispring.domain.repository.ClienteRepository;
import com.firstapispring.api.assembler.EntregaAssembler;
import com.firstapispring.api.representarioModels.DestinatarioRepresentarion;
import com.firstapispring.api.representarioModels.EntregaRepresentarion;
import com.firstapispring.domain.exeption.NegocioException;

import com.firstapispring.domain.model.Cliente;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EntregaService {

    private EntregaRepository entregaRepository;
    private ClienteService clienteService;


    private EntregaAssembler entregaAssembler;

    @Transactional
    public Entrega solicitar(Entrega entrega) {

        Cliente cliente = clienteService.buscar(entrega.getCliente().getId());      
                
        
        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        
        return entregaRepository.save(entrega);
    }

    @Transactional
    public ResponseEntity<EntregaRepresentarion> buscar(Long id){
        Entrega entrega = entregaRepository.findById(id).orElseThrow(() -> new NegocioException("Entrega não encontrada"));
        EntregaRepresentarion entregaRepresentario = entregaAssembler.toModel(entrega);
        return ResponseEntity.ok(entregaRepresentario);

    }
    
    
}
