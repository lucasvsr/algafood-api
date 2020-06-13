package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Estado> listar() {
		
		return manager.createQuery("FROM Estado c", Estado.class)
					  .getResultList();
		
	}
	
	@Override
	public Estado buscar(Long id) {
		
		return manager.find(Estado.class, id);
		
	}
	
	@Transactional
	@Override
	public Estado adicionar(Estado entidade) {
		
		return manager.merge(entidade);
		
	}
	
	@Transactional
	@Override
	public void remover(Long id) {
		
		Estado estado = buscar(id);
		
		if(estado == null) throw new EmptyResultDataAccessException(1);
		
		manager.remove(buscar(id));
		
	}

	@Override
	public Estado buscar(String nome) throws EmptyResultDataAccessException {
		
		return manager.createQuery("FROM Estado c WHERE c.nome = :nome", Estado.class)
					  .setParameter("nome", nome)
					  .getSingleResult();
		
	}

}
