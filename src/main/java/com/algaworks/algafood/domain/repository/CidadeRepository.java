package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
	Cidade findByNomeAndEstado(String nome, Estado estado);
	
}
