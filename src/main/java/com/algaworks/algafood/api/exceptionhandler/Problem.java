package com.algaworks.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

//Classe montada seguindo o padrão Problem Details for HTTP APIs
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

	private Integer status;
	private String type;
	private String title;
	private String detail;
	
	private String userMessage;
	@Builder.Default //Toda vez que a classe for instanciada, vai ter o timestamp
	private LocalDateTime timestamp = LocalDateTime.now();
	
}
