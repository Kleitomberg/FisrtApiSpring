package com.firstapispring.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable //PODE SER USADA COMO EMBEDDED EM OUTRAS CLASSES
public class Destinatario {

    @Column(name = "nome_destinatario")
    private String nome;

    @Column(name = "logradouro_destinatario")
    private String logradouro;

    @Column(name = "numero_destinatario")
    private String numero;

    @Column(name = "complemento_destinatario")
    private String complemento;

    @Column(name = "bairro_destinatario")
    private String bairro;

}
