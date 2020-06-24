package com.algaworks.algafood.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.algaworks.algafood.domain.repository.CustomJpaRepository;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> 
											implements CustomJpaRepository<T, ID> {

	private EntityManager manager;
	
	public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager manager) {
		
		super(entityInformation, manager);
		this.manager = manager;
		
	}

	@Override
	public Optional<T> buscarPrimeiro() {
		
		var jpql = "from " + getDomainClass().getName();
		
		T entity = manager.createQuery(jpql, getDomainClass())
						  .setMaxResults(1)
						  .getSingleResult();
		
		return Optional.ofNullable(entity);
		
	}

}
