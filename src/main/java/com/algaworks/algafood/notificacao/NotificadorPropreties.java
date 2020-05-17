package com.algaworks.algafood.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificador.email")
//A partir de agora, todos as propriedades com este prefixo podem ser carregadas por aqui, basta criar a variavel com o padrão. Caso a propriedade teermine com xxxx-xxxx, crie a variavel com o padrão xxxXxxx, como no exemplo abaixo
public class NotificadorPropreties {
	
	/**
	 * Host do servidor.
	 * notificador.email.host-servidor
	 */
	private String hostServidor;
	/**
	 * Porta do servidor. Valor padrão definido em classe: 25
	 * notificador.email.porta-servidor
	 */
	private Integer portaServidor = 25;	
	
	public String getHostServidor() {
		return hostServidor;
	}
	public void setHostServidor(String hostServidor) {
		this.hostServidor = hostServidor;
	}
	public Integer getPortaServidor() {
		return portaServidor;
	}
	public void setPortaServidor(Integer portaServidor) {
		this.portaServidor = portaServidor;
	}
	
}
