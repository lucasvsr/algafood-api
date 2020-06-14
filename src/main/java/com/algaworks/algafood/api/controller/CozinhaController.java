package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

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

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController //Esta anotação é a junção de @Controller e @ResponseBody
@RequestMapping(value = "/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository repository;
	
	@Autowired
	private CadastroCozinhaService service;
	
	@GetMapping
	public List<Cozinha> listar() {
		
		return repository.findAll();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {

		Optional<Cozinha> cozinha = repository.findById(id);
		
		if(cozinha.isPresent()) return ResponseEntity.ok(cozinha.get());
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		
		return service.salvar(cozinha);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id,
											 @RequestBody Cozinha atualizada) {
		
		Optional<Cozinha> atual = repository.findById(id);
		
		if(atual.isPresent()) {
			
			BeanUtils.copyProperties(atualizada, atual.get(), "id"); //Copia os dados do item atualizado para o atual (na base). A partir do terceiro parâmetro, passamos o nome das propriedades que não serão copiadas.
			
			return ResponseEntity.ok(service.salvar(atual.get()));
			
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
				
		try {
			
			service.excluir(id);
			
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
			
		}

	}
}
