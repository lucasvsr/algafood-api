package com.algaworks.algafood.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;
	private static final String ESTADO_NÃO_ENCONTRADO = "Estado de código %d não encontrado";


	public EstadoNaoEncontradoException(String mensagem) {
		
		super(mensagem);
		
	}
	
	public EstadoNaoEncontradoException(Long id) {
		
		this(String.format(ESTADO_NÃO_ENCONTRADO, id));
		
	}

}