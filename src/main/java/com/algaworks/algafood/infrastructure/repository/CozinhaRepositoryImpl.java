package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cozinha> listar() {
		
		return manager.createQuery("FROM Cozinha c", Cozinha.class)
					  .getResultList();
		
	}
	
	@Override
	public Cozinha buscar(Long id) {
		
		return manager.find(Cozinha.class, id);
		
	}
	
	@Transactional
	@Override
	public Cozinha adicionar(Cozinha cozinha) {
		
		return manager.merge(cozinha);
		
	}
	
	@Transactional
	@Override
	public void remover(Cozinha cozinha) {
		
		manager.remove(buscar(cozinha.getId()));
		
	}

}