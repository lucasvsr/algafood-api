package com.algaworks.algafood.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.modelo.Cliente;
import com.algaworks.algafood.notificacao.NivelUrgencia;
import com.algaworks.algafood.notificacao.Notificador;
import com.algaworks.algafood.notificacao.TipoDoNotificador;

//@Component
public class AtivacaoClienteService {

	@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
	@Autowired(required = false) // ESTA ANOTAÇÃO PODE SER USADA NO CONSTRUTOR, NO ATRIBUTO OU NO SET
	private Notificador notificador;

//	@PostConstruct
	public void init() {

		System.out.println("INICIANDO");

	}

//	@PreDestroy
	public void destroy() {

		System.out.println("DESTRUINDO");

	}

	public void ativar(Cliente cliente) {

		cliente.ativar();

		notificador.notificar(cliente, "Seu cadastro no sistema está ativo");

	}
}
