package com.algaworks.algafood.domain.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.CidadeJaCadastradaException;
import com.algaworks.algafood.domain.exception.CidadeSemEstadoException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository repository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	public Cidade adicionar(Cidade cidade) {

		try {
			
			if(cidade.getEstado() == null)
				throw new CidadeSemEstadoException("Não é possível atualizar/incluir uma cidade sem um estado");
			
			if (repository.buscar(cidade.getNome(), estadoRepository.buscar(cidade.getEstado().getId())) != null)
				throw new CidadeJaCadastradaException("Cidade já cadastrada");

		} catch (NoResultException e) {

			return repository.adicionar(cidade);

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
