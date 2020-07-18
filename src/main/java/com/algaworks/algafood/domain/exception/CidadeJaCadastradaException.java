package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CidadeJaCadastradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CidadeJaCadastradaException(String mensagem) {
		
		super(mensagem);
		
	}

}
