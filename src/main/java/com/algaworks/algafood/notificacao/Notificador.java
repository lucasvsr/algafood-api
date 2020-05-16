package com.algaworks.algafood.notificacao;

import com.algaworks.algafood.modelo.Cliente;

public interface Notificador {
	
	public static String URGENTE = "urgente";
	public static String NORMAL = "normal";

	void notificar(Cliente cliente, String mensagem);

}