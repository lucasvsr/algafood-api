package com.algaworks.algafood.domain.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EstadoJaCadastradoException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	@Autowired
	private EstadoRepository repository;

	public Estado adicionar(Estado estado) {
		
		try {
			
			if(estado.getId() != null) //SE O ID N FOR NULO É UMA ATUALIZAÇÃO
				return repository.adicionar(estado);
			
			if(repository.buscar(estado.getNome()) != null)
				throw new EstadoJaCadastradoException("Estado já cadastrado");
				
			
		} catch (NoResultException e) {
			
			return repository.adicionar(estado);
			
		}
		
		return null;
		
	}
	
	public void remover(Long id) {
		
		try {

			repository.remover(id);

		} catch (EmptyResultDataAccessException e) {
			
			throw new EntidadeNaoEncontradaException(String.format("Estado de código %d não encontrado", id));
			
		} catch (DataIntegrityViolationException e) {
			
			throw new EntidadeEmUsoException(String.format("Estado de código %d não pode ser removido, pois está em uso", id));

		}
		
	}

}
