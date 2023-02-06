package com.firstapispring.api.representarioModels;

import lombok.Getter;
import lombok.Setter;
import java.time.OffsetDateTime;

@Getter
@Setter
public class OcorrenciaRepresentation {

    private Long id;
    private String descricao;
    private OffsetDateTime dataRegistro;
    
    
}
