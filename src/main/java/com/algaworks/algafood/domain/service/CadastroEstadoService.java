package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EstadoJaCadastradoException;
import com.algaworks.algafood.domain.exception.EstadoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	private static final String ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso";
	private static final String ESTADO_JÁ_CADASTRADO = "Estado já cadastrado";
	@Autowired
	private EstadoRepository repository;

	public Estado adicionar(Estado estado) {
		
		if(repository.findByNome(estado.getNome()) != null)
			throw new EstadoJaCadastradoException(ESTADO_JÁ_CADASTRADO);
		
		return repository.save(estado);
		
	}
	
	public Estado buscar(Long id) {
		
		return repository.findById(id).orElseThrow(
				() -> new EstadoNaoEncontradoException(id));
		
	}
	
	public void remover(Long id) {
		
		try {

			repository.delete(buscar(id));

		} catch (DataIntegrityViolationException e) {
			
			throw new EntidadeEmUsoException(String.format(ESTADO_EM_USO, id));

		}
		
	}

}
