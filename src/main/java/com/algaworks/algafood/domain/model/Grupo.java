package com.algaworks.algafood.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data // LOMBOK: Serve para criar automáticamente os getters, setters, toString, equals e hashcode
@EqualsAndHashCode(onlyExplicitlyIncluded = true) //ao colocar esta tag, sobrescrevemos o @Data para deixar claro quais atributos vamos usar para a geração desses dois métodos
public class Grupo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "grupo_permissao",
			   joinColumns = @JoinColumn(name = "grupo_id"),
			   inverseJoinColumns = @JoinColumn(name = "permissao_id"))
	private List<Permissao> permissoes;

}
