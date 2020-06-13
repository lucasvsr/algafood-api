package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cidade> listar() {

		return manager.createQuery("FROM Cidade c", Cidade.class).getResultList();

	}

	@Override
	public Cidade buscar(Long id) {

		return manager.find(Cidade.class, id);

	}

	@Override
	public Cidade buscar(String nome, Estado estado) throws EmptyResultDataAccessException {

		return manager.createQuery("FROM Cidade c " + "WHERE c.nome = :nome " + "AND c.estado = :estado", Cidade.class)
				.setParameter("nome", nome).setParameter("estado", estado).getSingleResult();

	}

	@Transactional
	@Override
	public Cidade adicionar(Cidade entidade) {

		return manager.merge(entidade);

	}

	@Override
	public void remover(Long id) {

		Cidade cidade = buscar(id);

		if (cidade == null)
			throw new EmptyResultDataAccessException(1);

		manager.remove(buscar(id));

	}

}
