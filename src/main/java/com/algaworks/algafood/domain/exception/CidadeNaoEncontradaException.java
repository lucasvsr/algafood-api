package com.algaworks.algafood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {


	private static final long serialVersionUID = 1L;
	private static final String CIDADE_NÃO_ENCONTRADA = "Cidade de código %d não encontrada";

	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CidadeNaoEncontradaException(Long id) {
		
		this(String.format(CIDADE_NÃO_ENCONTRADA, id));
		
	}

}
