package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;

public interface CidadeRepository {
	
	List<Cidade> listar();
	
	Cidade buscar(Long id);
	
	/** Busca a cidade pelo nome e o estado passado*/
	Cidade buscar(String nome, Estado estado);
	
	Cidade adicionar(Cidade entidade);
	
	void remover(Long id);

}
