package com.firstapispring.api.assembler;

import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;
import com.firstapispring.api.representarioModels.input.OcorrenciaInput;
import com.firstapispring.domain.model.Ocorrencia;
import com.firstapispring.api.representarioModels.OcorrenciaRepresentation;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OcorrenciaAssembler {

    private ModelMapper modelMapper;

    public OcorrenciaRepresentation toRepresentation(Ocorrencia ocorrencia) {
        return modelMapper.map(ocorrencia, OcorrenciaRepresentation.class);
    }

    public List<OcorrenciaRepresentation> toCollectionRepresentations(List<Ocorrencia> ocorrencias) {
        return ocorrencias.stream()
                .map(ocorrencia -> toRepresentation(ocorrencia))
                .collect(Collectors.toList());
    }

    
}
