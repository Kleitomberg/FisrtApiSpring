package com.firstapispring.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	
	@Column(name = "nome")
	@NotBlank(message = "Nome é obrigatório")
	@Size(max = 50)
	private String nome;
	
	@Column(name="email")
	@NotBlank(message = "Email é obrigatório")
	@Email
	@Size(max = 255)
	private String email;
	
	@Column(name="telefone")
	
	@NotBlank(message = "Telefone é obrigatório")	
	@Size(max = 20)
	private String telefone;

}
