package com.firstapispring.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.firstapispring.domain.ValidationGroups;
import com.firstapispring.domain.exeption.NegocioException;

//default

import jakarta.validation.groups.Default;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList; 
import jakarta.persistence.CascadeType;



@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "entregas")
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
    @NotNull(message = "O campo Cliente não pode ser nulo")
    @ManyToOne
    private Cliente cliente;

    @Valid // para que o JPA valide o objeto TODOS OS CAMPOS  embutido E não apenas o objeto em si
    @NotNull(message = "O campo Destinatário não pode ser nulo")
    @Embedded // para que o JPA entenda que o Destinatario é um tipo embutido, ou seja, que não é uma entidade
    private Destinatario destinatario;

    @NotNull(message = "O campo taxa não pode ser nulo")
    private BigDecimal taxa;

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataPedido;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataFinalizacao;

    @OneToMany(mappedBy = "entrega",cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias = new ArrayList<>();


    public Ocorrencia adicionarOcorrencia(String descricao) {
       Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);
        getOcorrencias().add(ocorrencia);
        return ocorrencia;
    }

    public void finalizar() {
        if (naoPodeSerFinalizada()) {
            throw new NegocioException("Entrega não pode ser finalizada");
        }

        setStatus(StatusEntrega.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }

    public boolean podeSerFinalizada() {
        return StatusEntrega.PENDENTE.equals(getStatus());
    }

    public boolean naoPodeSerFinalizada() {
        return !podeSerFinalizada();
    }

    
}
