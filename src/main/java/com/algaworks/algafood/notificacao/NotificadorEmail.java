package com.algaworks.algafood.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.modelo.Cliente;


@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {
	
	@Autowired
	private NotificadorPropreties propreties;
	
	public NotificadorEmail() {
		
		System.out.println("NotificadorEmail REAL ");
		
	}
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.println("Host: " + propreties.getHostServidor());
		System.out.println("Porta: " + propreties.getPortaServidor());
		
		System.out.printf("Notificando %s através do e-mail %s: %s\n",
				cliente.getNome(), cliente.getEmail(), mensagem);
		
	}

}
