package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.infrastructure.domain.repository.RestauranteRepositoryQueries;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, 
											   RestauranteRepositoryQueries,
											   JpaSpecificationExecutor<Restaurante> {
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal menor, BigDecimal maior);
	
	//@Query("FROM Restaurante r WHERE r.nome LIKE %:nome% AND r.cozinha.id = :id") //ESSA CONSULTA FOI EXTERNALIZADA PARA O resources/META-INF/orm.xml
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);
	

}
