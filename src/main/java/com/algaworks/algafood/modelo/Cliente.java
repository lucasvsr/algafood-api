package com.algaworks.algafood.modelo;

public class Cliente {

	private String nome;
	private String email;
	private boolean ativo = false;
	private String telefone;
	
	public Cliente(String nome, String email, String telefone) {
		
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public void ativar() {
		
		setAtivo(true);
		
	}
	
}
