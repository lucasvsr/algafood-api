package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Service // A classe será um componente destinado para as regras de negócio da entidade
public class CadastroCozinhaService {

	private static final String COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private CozinhaRepository repository;

	public Cozinha salvar(Cozinha cozinha) {

		return repository.save(cozinha);

	}

	public Cozinha buscar(Long id) {

		return repository.findById(id).orElseThrow(
				() -> new CozinhaNaoEncontradaException(id));

	}

	public void excluir(Long id) {

		try {

			repository.delete(buscar(id));

		}  catch (DataIntegrityViolationException e) {

			throw new EntidadeEmUsoException(
					String.format(COZINHA_EM_USO, id));

		}

	}

}
