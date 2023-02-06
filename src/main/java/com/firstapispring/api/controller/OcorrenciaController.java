package com.firstapispring.api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.firstapispring.api.representarioModels.OcorrenciaRepresentation;
import com.firstapispring.api.representarioModels.input.OcorrenciaInput;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.firstapispring.domain.model.Entrega;
import com.firstapispring.domain.model.Ocorrencia;
import com.firstapispring.domain.service.OcorrenciaService;
import com.firstapispring.api.assembler.OcorrenciaAssembler;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class OcorrenciaController {

    private OcorrenciaService ocorrenciaService;
    private OcorrenciaAssembler ocorrenciaAssembler;

    @PostMapping("/{entregaId}/ocorrencias")
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaRepresentation Registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
       Ocorrencia newOcorrencia = ocorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());
        return ocorrenciaAssembler.toRepresentation(newOcorrencia);
    }

    @GetMapping("/{entregaId}/ocorrencias")
    public List<OcorrenciaRepresentation> listar(@PathVariable Long entregaId) {
        Entrega entrega = ocorrenciaService.buscarEntrega(entregaId);
        return ocorrenciaAssembler.toCollectionRepresentations(entrega.getOcorrencias());
        
    }
    



    
}
