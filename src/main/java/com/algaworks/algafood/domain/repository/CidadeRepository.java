package com.algaworks.algafood.domain.repository;

import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;

@Repository
public interface CidadeRepository extends CustomJpaRepository<Cidade, Long> {
	
	Boolean existsByNomeAndEstado(String nome, Estado estado);
	
}
