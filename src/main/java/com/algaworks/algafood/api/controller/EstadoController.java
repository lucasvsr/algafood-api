package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@RestController //Esta anotação é a junção de @Controller e @ResponseBody
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository repository;
	
	@GetMapping
	public List<Estado> listar() {
		
		return repository.listar();
		
	}

}
