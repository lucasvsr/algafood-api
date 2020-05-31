package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@RestController //Esta anotação é a junção de @Controller e @ResponseBody
@RequestMapping(value = "/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository repository;
	
	@GetMapping
	public List<Cozinha> listar() {
		
		return repository.listar();
		
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlWrapper listarXml() {
		
		return new CozinhasXmlWrapper(repository.listar());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {

		Cozinha cozinha = repository.buscar(id);
		
		if(cozinha != null) return ResponseEntity.ok(cozinha);
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		
		return repository.adicionar(cozinha);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id,
											 @RequestBody Cozinha atualizada) {
		
		Cozinha atual = repository.buscar(id);
		
		if(atual != null) {
			
			BeanUtils.copyProperties(atualizada, atual, "id"); //Copia os dados do item atualizado para o atual (na base). A partir do terceiro parâmetro, passamos o nome das propriedades que não serão copiadas.
			
			return ResponseEntity.ok(repository.adicionar(atual));
			
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
		
		try {
			
			Cozinha cozinha = repository.buscar(id);
			
			if(cozinha != null) {
				
				repository.remover(cozinha);
				
				return ResponseEntity.noContent().build();
				
			}
			
			
		} catch (DataIntegrityViolationException e) {
				
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
	
		}
		
		return ResponseEntity.notFound().build();
				
	}

}
