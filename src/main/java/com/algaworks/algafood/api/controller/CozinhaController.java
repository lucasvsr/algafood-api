package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
		
		HttpHeaders headers = new HttpHeaders();
		Cozinha cozinha = repository.buscar(id);
		
		headers.add("Teste", "Testando");
		
		if(id == 1) return ResponseEntity.status(HttpStatus.NOT_FOUND)
										 .headers(headers)
										 .build();
		
		if(id == 2) return ResponseEntity.ok(cozinha);
		
		return null;
		
	}

}
