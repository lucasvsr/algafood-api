package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service // A classe será um componente destinado para as regras de negócio da entidade
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository repository;

	@Autowired
	private CadastroCozinhaService cozinhaService;

	public Restaurante salvar(Restaurante restaurante) {

		Cozinha cozinha = cozinhaService.buscar(restaurante.getCozinha().getId());

		restaurante.setCozinha(cozinha);

		return repository.save(restaurante);

	}

	public Restaurante buscar(Long id) {

		return repository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format("Restaurante de código %d não foi encontrado", id)));

	}

	public void remover(Long id) {
		
		repository.delete(buscar(id));
		
	}

}
