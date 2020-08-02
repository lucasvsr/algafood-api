package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	ENTIDADE_NAO_ENCONTRADA("entidade-nao-encontrada", "Entidade não encontrada"),
	NEGOCIO_EXCEPTION("erro-de-negocio", "Erro de negócio"),
	ENTIDADE_EM_USO("entidade-em-uso", "Entidade em uso"), 
	MENSAGEM_INCOMPREENSIVEL("mensagem-incompreensivel", "Mensagem incompreensível");
	
	private String uri;
	private String title;
	
	
	private ProblemType(String uri, String title) {
		
		this.uri = "https://algafood.com.br/" + uri;
		this.title = title;
		
	}
	
	

}
