package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service // A classe será um componente destinado para as regras de negócio da entidade
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository repository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Restaurante salvar(Restaurante restaurante) {
		
		if(cozinhaRepository.buscar(restaurante.getCozinha().getId()) == null)
			throw new EntidadeNaoEncontradaException(String.format("Cozinha de código %d não foi encontrada",
													 restaurante.getCozinha().getId()));
		
		return repository.adicionar(restaurante);

	}

}
