package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.infrastructure.domain.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		
		var jpql = new StringBuilder();
		var parametros = new HashMap<String, Object>();
		TypedQuery<Restaurante> query;
			
			jpql.append("FROM Restaurante r WHERE 0 = 0 ");
			
			if(StringUtils.hasLength(nome)) {
				
				jpql.append("AND nome like :nome ");
				parametros.put("nome", nome);
				
			}
			if(taxaInicial != null) {
				
				jpql.append("AND taxaFrete >= :taxaInicial ");
				parametros.put("taxaInicial", taxaInicial);
				
			}
			if(taxaFinal != null) {
				
				jpql.append("AND taxaFrete <= :taxaFinal ");
				parametros.put("taxaFinal", taxaFinal);
				
			}
			
			jpql.append("ORDER BY taxaFrete ASC");
			
			query = manager.createQuery(jpql.toString(), Restaurante.class);
			
			parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
			
			return query.getResultList();
		
	}

}
