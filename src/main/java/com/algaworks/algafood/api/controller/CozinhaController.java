package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@RestController //Esta anotação é a junção de @Controller e @ResponseBody
@RequestMapping(value = "/cozinhas", produces = { MediaType.APPLICATION_JSON_VALUE })
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository repository;
	
	@GetMapping
	public List<Cozinha> listar() {
		
		return repository.listar();
		
	}

}
