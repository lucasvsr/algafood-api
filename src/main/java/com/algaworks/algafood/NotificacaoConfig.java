package com.algaworks.algafood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.notificacao.NotificadorEmail;

//ASSIM CONFIGURAMOS UM COMPONENTE VIA CLASSE CONFIGURAÇÃO ESPECIFICA
@Configuration
public class NotificacaoConfig {
	
	@Bean
	public NotificadorEmail notificadorEmail() {
		
		NotificadorEmail notificador = new NotificadorEmail("smtp.qualquer.com.br");
		notificador.setCaixaAlta(true);
		
		return notificador;
		
	}

}
