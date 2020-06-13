package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.CidadeJaCadastradaException;
import com.algaworks.algafood.domain.exception.CidadeSemEstadoException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.service.CadastroCidadeService;

@RestController //Esta anotação é a junção de @Controller e @ResponseBody
@RequestMapping(value = "/cidades")
public class CidadeController {
	
	@Autowired
	private CidadeRepository repository;
	
	@Autowired
	private CadastroCidadeService service;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Cidade> listar() {
		
		return repository.listar();
		
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
		
		try {

			return ResponseEntity.status(HttpStatus.CREATED).body(service.adicionar(cidade));

		} catch (CidadeJaCadastradaException e) {

			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade atualizada) {

		Cidade atual = repository.buscar(id);

		if (atual != null) {

			BeanUtils.copyProperties(atualizada, atual, "id");
			
			try {
				
				return ResponseEntity.ok(service.adicionar(atual));
				
			} catch (CidadeSemEstadoException e) {
				
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
									 .body(e.getMessage());
				
			} catch (CidadeJaCadastradaException e) {
				
				return ResponseEntity.status(HttpStatus.CONFLICT)
						 			 .body(e.getMessage());
				
			}

		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		
		try {

			service.remover(id);

			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {

			return ResponseEntity.status(HttpStatus.CONFLICT).build();

		}
		
	}
	
}
