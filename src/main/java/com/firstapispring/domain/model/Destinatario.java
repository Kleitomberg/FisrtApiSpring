package com.firstapispring.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable //PODE SER USADA COMO EMBEDDED EM OUTRAS CLASSES
public class Destinatario {

    @NotBlank
    @Column(name = "nome_destinatario")
    private String nome;

    @NotBlank
    @Column(name = "logradouro_destinatario")
    private String logradouro;

    @NotBlank
    @Column(name = "numero_destinatario")
    private String numero;

    @NotBlank
    @Column(name = "complemento_destinatario")
    private String complemento;

    @NotBlank
    @Column(name = "bairro_destinatario")
    private String bairro;

}
