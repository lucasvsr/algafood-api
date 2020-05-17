package com.algaworks.algafood.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.service.ClienteAtivadoEvent;

@Component
public class EmissaoNotaFiscalService {
	
	@EventListener
	public void clienteAtivoListener(ClienteAtivadoEvent event) {
		
		System.out.println("Emitindo nota fiscal para o cliente " + event.getCliente().getNome());
		
	}

}
