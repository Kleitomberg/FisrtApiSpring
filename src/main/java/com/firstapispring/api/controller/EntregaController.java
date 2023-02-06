package com.firstapispring.api.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.firstapispring.api.assembler.EntregaAssembler;
import com.firstapispring.api.representarioModels.EntregaRepresentarion;
import com.firstapispring.api.representarioModels.input.EntregaInput;
import com.firstapispring.domain.model.Entrega;
import com.firstapispring.domain.service.EntregaService;

import jakarta.validation.Valid;

import com.firstapispring.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private EntregaService entregaService;
    private EntregaRepository entregaRepository;

    private EntregaAssembler entregaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaRepresentarion solicitar(@Valid @RequestBody EntregaInput entrega) {
        Entrega novaEntrega = entregaAssembler.toEntity(entrega);
        return entregaAssembler.toModel(entregaService.solicitar(novaEntrega));
    }

    @GetMapping
    public List<EntregaRepresentarion> listar() {       
        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaRepresentarion> buscar(@PathVariable Long entregaId) {
        return entregaService.buscar(entregaId);
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizarEntrega(@PathVariable Long entregaId) {
        entregaService.finalizar(entregaId);
    }
    
}
