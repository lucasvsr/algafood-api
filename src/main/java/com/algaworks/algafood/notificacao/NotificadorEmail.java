package com.algaworks.algafood.notificacao;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.modelo.Cliente;

@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {
	
	public NotificadorEmail() {
		
		System.out.println("NotificadorEmail REAL ");
		
	}
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.printf("Notificando %s através do e-mail %s: %s\n",
				cliente.getNome(), cliente.getEmail(), mensagem);
		
	}

}
