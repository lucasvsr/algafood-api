package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.CidadeJaCadastradaException;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {

	private static final String CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";
	private static final String CIDADE_JÁ_CADASTRADA = "Cidade já cadastrada";
	
	@Autowired
	private CidadeRepository repository;
	
	@Autowired
	private CadastroEstadoService estadoService;
	

	public Cidade adicionar(Cidade cidade) {
		
		Estado estado = estadoService.buscar(cidade.getEstado().getId());
		
		cidade.setEstado(estado);
		
		if(repository.existsByNomeAndEstado(cidade.getNome(), cidade.getEstado()))
			throw new CidadeJaCadastradaException(CIDADE_JÁ_CADASTRADA);
			
		return repository.save(cidade);
			

	}
	
	public Cidade buscar(Long id) {
		
		return repository.findById(id).orElseThrow(
				() -> new CidadeNaoEncontradaException(id));
		
	}

	public void remover(Long id) {
		
		try {

			repository.delete(buscar(id));
			
		} catch (DataIntegrityViolationException e) {
			
			throw new EntidadeEmUsoException(String.format(CIDADE_EM_USO, id));

		}	
		
	}

}
