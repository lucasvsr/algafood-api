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
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController // Esta anotação é a junção de @Controller e @ResponseBody
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository repository;

	@Autowired
	private CadastroEstadoService service;

	@GetMapping
	public List<Estado> listar() {

		return repository.findAll();

	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Estado estado) {

		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(service.adicionar(estado));


	}

	@PutMapping("/{id}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long id,
											@RequestBody Estado atualizado) {

		Estado atual = service.buscar(id);

		BeanUtils.copyProperties(atualizado, atual, "id");

		return ResponseEntity.ok(service.adicionar(atual));

	}

	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {

		service.remover(id);

	}

}
