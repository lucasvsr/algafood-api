package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	private static final String RESTAURANTE_NÃO_FOI_ENCONTRADO = "Restaurante de código %d não foi encontrado";

	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public RestauranteNaoEncontradoException(Long id) {
		this(String.format(RESTAURANTE_NÃO_FOI_ENCONTRADO, id));
	}

}
