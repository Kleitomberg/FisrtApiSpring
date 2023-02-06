package com.firstapispring.api.exeptionhandler;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter

public class Problema {
	
	private Integer status;
	private OffsetDateTime data;
	private String titulo;
	private List<Campo> campos;
	
	@Getter
	@AllArgsConstructor
	public static class Campo{
		private String nome;
		private String mensagem;
	}
}
