package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteComFreteGratisSpec;
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteComNomeSemelhanteSpec;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController // Esta anotação é a junção de @Controller e @ResponseBody
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository repository;

	@Autowired
	private CadastroRestauranteService service;

	@GetMapping
	public List<Restaurante> listar() {

		return repository.findAll();

	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {

		Optional<Restaurante> restaurante = repository.findById(id);

		if(restaurante.isPresent()) return ResponseEntity.ok(restaurante.get());

		return ResponseEntity.notFound().build();

	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {

		try {

			return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(restaurante));

		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.badRequest()
								 .body(e.getMessage());

		}

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, 
									   @RequestBody Restaurante atualizado) {
		
		Optional<Restaurante> atual = repository.findById(id);
		
		if(atual.isPresent()) {
			
			BeanUtils.copyProperties(atualizado, atual.get(), "id"); //Copia os dados do item atualizado para o atual (na base). A partir do terceiro parâmetro, passamos o nome das propriedades que não serão copiadas.
			
			return ResponseEntity.ok(service.salvar(atual.get()));
			
		}
		
		return ResponseEntity.notFound().build();		
		
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> alterar(@PathVariable Long id, 
									 @RequestBody Map<String, Object> campos) {
		
		Optional<Restaurante> atual = repository.findById(id);
		
		if(atual.isEmpty())
			return ResponseEntity.notFound().build();
		
		merge(campos, atual.get());
		
		return atualizar(id, atual.get());
		
	}
	
	@GetMapping("/taxa")
	public List<Restaurante> buscarPorTaxas(@RequestParam BigDecimal menor, @RequestParam BigDecimal maior) {
		
		return repository.findByTaxaFreteBetween(menor, maior);
		
	}
	
	@GetMapping("/nome-e-cozinha")
	public List<Restaurante> buscarPorNomeCozinha(@RequestParam String nome, @RequestParam Long cozinha){
		
		return repository.consultarPorNome(nome, cozinha);
		
	}
	
	@GetMapping("/nome-e-taxa")
	public List<Restaurante> buscarPorNomeCozinha(@RequestParam String nome, 
												  @RequestParam BigDecimal taxaInicial,
												  @RequestParam BigDecimal taxaFinal){
		
		return repository.find(nome, taxaInicial, taxaFinal);
		
	}
	
	@GetMapping("/nome-semelhante")
	public List<Restaurante> buscarPorNomeSemelhante(@RequestParam String nome) {
		
		var comFreteGratis = new RestauranteComFreteGratisSpec();
		var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);
		
		return repository.findAll(comFreteGratis.and(comNomeSemelhante));
		
	}
	
	private void merge(Map<String, Object> origem, Restaurante destino) {
		
		ObjectMapper mapper = new ObjectMapper();
		Restaurante original = mapper.convertValue(origem, Restaurante.class); //CONVERTE O MAP EM ENTIDADE
		
		origem.forEach((nome, valor) -> {
			
			Field campo = ReflectionUtils.findField(Restaurante.class, nome); //PEGA O CAMPO DA ENTIDADE, O CAMPO N O VALOR
			campo.setAccessible(true); // SE O CAMPO ESTIVER PRIVADO, PRECISA SETAR ESSE MÉTODO PARA TRUE
			
			Object novoValor = ReflectionUtils.getField(campo, original); //PEGA O CAMPO E ATRIBUI O VALOR CONTIDO NO OBJETO CONVERTIDO PARA ENTIDADE
			
			ReflectionUtils.setField(campo, destino, novoValor); //PEGA O CAMPO DO OBJETO DESTINO E ATRIBUI O VALOR CONVERTIDO
			
		});
		
	}

}
