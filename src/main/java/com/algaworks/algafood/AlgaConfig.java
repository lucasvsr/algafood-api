package com.algaworks.algafood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.notificacao.NotificadorEmail;
import com.algaworks.algafood.service.AtivacaoClienteService;

//@Configuration
//ASSIM CONFIGURAMOS OS COMPONENTES NUMA UNICA CLASSE, SEM ANOTAÇÃO
public class AlgaConfig {
	
	@Bean
	public NotificadorEmail notificadorEmail() {
		
		NotificadorEmail notificador = new NotificadorEmail("smtp.qualquer.com.br");
		notificador.setCaixaAlta(true);
		
		return notificador;
		
	}
	
	@Bean
	public AtivacaoClienteService ativacaoClienteService() {
		
		return new AtivacaoClienteService(notificadorEmail());
		
	}

}
