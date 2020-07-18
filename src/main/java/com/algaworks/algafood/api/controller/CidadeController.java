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
		
		return repository.findAll();
		
	}
	
	@GetMapping("/{id}")
	public Cidade buscar(@PathVariable Long id) {
		
		return service.buscar(id);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody Cidade cidade) {
		
		return service.adicionar(cidade);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade atualizada) {

		Cidade atual = service.buscar(id);

		BeanUtils.copyProperties(atualizada, atual, "id");
			
		return ResponseEntity.ok(service.adicionar(atual));
				
	}

	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {

		service.remover(id);
	}
	
}
