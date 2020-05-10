package com.algaworks.algafood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.notificacao.Notificador;
import com.algaworks.algafood.service.AtivacaoClienteService;

//ASSIM CONFIGURAMOS UM COMPONENTE VIA CLASSE CONFIGURAÇÃO ESPECIFICA
@Configuration
public class ServiceConfig {
	
	@Bean
	public AtivacaoClienteService ativacaoClienteService(Notificador notificador) {
		
		return new AtivacaoClienteService(notificador);
		
	}

}
