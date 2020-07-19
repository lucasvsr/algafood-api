package com.algaworks.algafood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	private static final String COZINHA_NÃO_ENCONTRADA = "Cozinha de código %d não encontrada";

	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CozinhaNaoEncontradaException(Long id) {
		
		this(String.format(COZINHA_NÃO_ENCONTRADA, id));
		
	}

}
