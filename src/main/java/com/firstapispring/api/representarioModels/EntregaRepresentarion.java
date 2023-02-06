package com.firstapispring.api.representarioModels;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.firstapispring.domain.model.Destinatario;
import com.firstapispring.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaRepresentarion {

    private Long id;
    private String nomeCliente;
    private DestinatarioRepresentarion destinatario;
    private BigDecimal taxa;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;

    
}
