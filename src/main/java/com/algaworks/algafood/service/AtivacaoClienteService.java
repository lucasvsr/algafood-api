package com.algaworks.algafood.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.modelo.Cliente;
import com.algaworks.algafood.notificacao.Notificador;

@Component
public class AtivacaoClienteService {
	
	@Qualifier(Notificador.URGENTE)
	@Autowired(required = false) //ESTA ANOTAÇÃO PODE SER USADA NO CONSTRUTOR, NO ATRIBUTO OU NO SET
	private Notificador notificador;
	
	public void ativar(Cliente cliente) {
		
		cliente.ativar();
		
		notificador.notificar(cliente, "Seu cadastro no sistema está ativo");

	}


}
