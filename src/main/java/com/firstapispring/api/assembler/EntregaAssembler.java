package com.firstapispring.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.firstapispring.api.representarioModels.EntregaRepresentarion;
import com.firstapispring.api.representarioModels.input.EntregaInput;
import com.firstapispring.domain.model.Entrega;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EntregaAssembler {

    private ModelMapper modelMapper;


    public EntregaRepresentarion toModel(Entrega entrega) {
        return modelMapper.map(entrega, EntregaRepresentarion.class);
    }


    public List<EntregaRepresentarion> toCollectionModel(List<Entrega> entregas) {
        return entregas.stream()
                .map(entrega -> toModel(entrega))
                .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaInput entregaInput) {
        return modelMapper.map(entregaInput, Entrega.class);
    }

    
}
