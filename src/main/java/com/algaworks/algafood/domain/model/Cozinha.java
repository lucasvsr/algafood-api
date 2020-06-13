package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

//@JsonRootName("cozinha") //PERMITE ALTERAR O NOME DO OBJETO XML QUANDO USAMOS ESSA REPRESENTAÇÃO NO SERVIÇO REST
@Entity
@Data // LOMBOK: Serve para criar automáticamente os getters, setters, toString, equals e hashcode
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //ao colocar esta tag, sobrescrevemos o @Data para deixar claro quais atributos vamos usar para a geração desses dois métodos
public class Cozinha {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include //Indica que este atributo deve ser usado na geração do equals e hashcode
	private Long id;
	
	@Column(nullable = false)
	private String nome;

}
