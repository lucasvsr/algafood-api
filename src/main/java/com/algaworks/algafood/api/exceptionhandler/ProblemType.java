package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	RECURSO_NAO_ENCONTRADO("recurso-nao-encontrado", "Recurso não encontrado"),
	NEGOCIO_EXCEPTION("erro-de-negocio", "Erro de negócio"),
	ENTIDADE_EM_USO("entidade-em-uso", "Entidade em uso"), 
	MENSAGEM_INCOMPREENSIVEL("mensagem-incompreensivel", "Mensagem incompreensível"), 
	ERRO_DE_SISTEMA("erro-inesperado", "Erro inesperado"), 
	DADOS_INVALIDOS("dados-invalidos", "Dados inválidos");
	
	private String uri;
	private String title;
	
	
	private ProblemType(String uri, String title) {
		
		this.uri = "https://algafood.com.br/" + uri;
		this.title = title;
		
	}
	
	

}
