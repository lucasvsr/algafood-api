package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> listar() {
		
		return manager.createQuery("FROM Restaurante c", Restaurante.class)
					  .getResultList();
		
	}
	
	@Override
	public Restaurante buscar(Long id) {
		
		return manager.find(Restaurante.class, id);
		
	}
	
	@Transactional
	@Override
	public Restaurante adicionar(Restaurante restaurante) {
		
		return manager.merge(restaurante);
		
	}
	
	@Transactional
	@Override
	public void remover(Restaurante restaurante) {
		
		manager.remove(buscar(restaurante.getId()));
		
	}

}