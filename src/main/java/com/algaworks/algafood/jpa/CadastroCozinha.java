package com.algaworks.algafood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cozinha;

@Component
public class CadastroCozinha {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Cozinha> listar() {
		
		return manager.createQuery("FROM Cozinha c", Cozinha.class)
					  .getResultList();
		
	}
	
	public Cozinha buscar(Long id) {
		
		return manager.find(Cozinha.class, id);
		
	}
	
	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		
		return manager.merge(cozinha);
		
	}
	
	@Transactional
	public void remover(Cozinha cozinha) {
		
		manager.remove(buscar(cozinha.getId()));
		
	}

}
