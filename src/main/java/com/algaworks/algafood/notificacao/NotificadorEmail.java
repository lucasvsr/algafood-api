package com.algaworks.algafood.notificacao;

import com.algaworks.algafood.modelo.Cliente;


public class NotificadorEmail implements Notificador {
	
	private boolean caixaAlta;
	private String servidor;
	
	public NotificadorEmail(String servidor) {
		
		this.servidor = servidor;
		
		System.out.println("Construtor chamado");
		
	}
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		if(caixaAlta) {
			
			mensagem = mensagem.toUpperCase();
			
		}
		
		System.out.printf("Notificando %s através do e-mail %s usando SMTP %s: %s\n",
				cliente.getNome(), cliente.getEmail(), servidor, mensagem);
		
	}

	public void setCaixaAlta(boolean caixaAlta) {
		this.caixaAlta = caixaAlta;
	}

}
