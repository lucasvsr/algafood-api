package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.algaworks.algafood.core.validation.Groups;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data // LOMBOK: Serve para criar automáticamente os getters, setters, toString, equals e hashcode
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //ao colocar esta tag, sobrescrevemos o @Data para deixar claro quais atributos vamos usar para a geração desses dois métodos
public class Estado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@NotNull(groups = Groups.EstadoID.class)
	private Long id;
	
	@Column(nullable = false)
	private String nome;

}
