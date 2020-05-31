package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Service //A classe será um componente destinado para as regras de negócio da entidade
public class CadastroCozinhaService {

	@Autowired
	private CozinhaRepository repository;
	
	public Cozinha salvar(Cozinha cozinha) {
		
		return repository.adicionar(cozinha);
		
	}
	
	
}
