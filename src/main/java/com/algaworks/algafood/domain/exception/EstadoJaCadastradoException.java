package com.algaworks.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EstadoJaCadastradoException extends NegocioException {

	private static final long serialVersionUID = 1L;
	
	public EstadoJaCadastradoException(String mensagem) {
		
		super(mensagem);
		
	}

}
