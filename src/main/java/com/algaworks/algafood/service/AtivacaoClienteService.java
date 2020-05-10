package com.algaworks.algafood.service;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.modelo.Cliente;
import com.algaworks.algafood.notificacao.Notificador;

@Component
public class AtivacaoClienteService {
	
	public AtivacaoClienteService(Notificador notificador) {
		
		this.notificador = notificador;
		
		System.out.println("AtivacaoClienteService: " + notificador);
		
	}

	private Notificador notificador;
	
	public void ativar(Cliente cliente) {
		
		cliente.ativar();
		
		notificador.notificar(cliente, "Cadastro efetuado com sucesso!");
		
	}

}
