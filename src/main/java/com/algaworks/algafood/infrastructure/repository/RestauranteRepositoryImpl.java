package com.algaworks.algafood.infrastructure.repository;

import static com.algaworks.algafood.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.algaworks.algafood.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.infrastructure.domain.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired @Lazy //A ANOTAÇÃO @LAZY É USADA QUANDO QUEREMOS INSTANCIAR ALGO APENAS QUANDO FOR NECESSÁRIO
	private RestauranteRepository repository;
	
	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		var predicados = new ArrayList<Predicate>();
		
		Root<Restaurante> root = criteria.from(Restaurante.class);
		
		if(StringUtils.hasLength(nome)) predicados.add(builder.like(root.get("nome"), "%" + nome + "%"));
		
		if(taxaInicial != null) predicados.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial));
		
		if(taxaFinal != null) predicados.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFinal));
		
		
		criteria.where(predicados.toArray(new Predicate[0]));
			
		return manager.createQuery(criteria).getResultList();
		
	}

	@Override
	public List<Restaurante> findComFreteGratis(String nome) {
		
		return repository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
		
	}

}
