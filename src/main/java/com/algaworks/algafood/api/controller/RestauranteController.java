package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
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
	public Restaurante buscar(@PathVariable Long id) {

		return service.buscar(id);

	}

	@PostMapping
	public Restaurante adicionar(@RequestBody Restaurante restaurante) {

		try {
			
			return service.salvar(restaurante);
		
		} catch (EntidadeNaoEncontradaException e) {
			
			throw new NegocioException(e.getMessage());
			
		}

	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Restaurante atualizar(@PathVariable Long id, 
									   @RequestBody Restaurante atualizado) {
		
		Restaurante atual = service.buscar(id);
		
		BeanUtils.copyProperties(atualizado, atual, "id", "formasPagamento", "endereco", "dataCadastro"); //Copia os dados do item atualizado para o atual (na base). A partir do terceiro parâmetro, passamos o nome das propriedades que não serão copiadas.
			
		try {
			
			return service.salvar(atual);
		
		} catch (EntidadeNaoEncontradaException e) {
			
			throw new NegocioException(e.getMessage());
			
		}
					
	}
	
	@PatchMapping("/{id}")
	public Restaurante alterar(@PathVariable Long id, 
									 @RequestBody Map<String, Object> campos) {
		
		Restaurante atual = service.buscar(id);
		
		merge(campos, atual);
		
		return atualizar(id, atual);
		
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		
		service.remover(id);
		
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
