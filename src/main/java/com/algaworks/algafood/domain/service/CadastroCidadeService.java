package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.CidadeJaCadastradaException;
import com.algaworks.algafood.domain.exception.CidadeSemEstadoException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

	private static final String CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";
	private static final String CIDADE_NÃO_ENCONTRADA = "Cidade de código %d não encontrada";
	private static final String CIDADE_JÁ_CADASTRADA = "Cidade já cadastrada";
	private static final String CIDADE_SEM_ESTADO = "Não é possível atualizar/incluir uma cidade sem um estado";
	
	@Autowired
	private CidadeRepository repository;
	

	public Cidade adicionar(Cidade cidade) {
		
		if(cidade.getId() != null)
			return repository.save(cidade);

		if(cidade.getEstado() == null)
			throw new CidadeSemEstadoException(CIDADE_SEM_ESTADO);
		
		if(repository.existsByNomeAndEstadoId(cidade.getNome(), cidade.getEstado().getId()))
			throw new CidadeJaCadastradaException(CIDADE_JÁ_CADASTRADA);
			
		return repository.save(cidade);
			

	}
	
	public Cidade buscar(Long id) {
		
		return repository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(CIDADE_NÃO_ENCONTRADA, id)));
		
	}

	public void remover(Long id) {
		
		try {

			repository.delete(buscar(id));
			
		} catch (DataIntegrityViolationException e) {
			
			throw new EntidadeEmUsoException(String.format(CIDADE_EM_USO, id));

		}	
		
	}

}
