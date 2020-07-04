package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	@JsonIgnore
	//@JsonIgnoreProperties({"hibernateLazyInitializer"}) //Permite fazer a consulta em casos de estratégias LAZY e serializa. O "hibernateLazyInitializer" é uma propriedade de classe gerada pelo hibernate em tempo de execução
	@ManyToOne(fetch = FetchType.LAZY) //POR PADRÃO A ESTRATÉGIA DE CONSULTA É EAGER
	@JoinColumn(nullable = false)
	private Cozinha cozinha;
	
	@JsonIgnore
	@Embedded
	private Endereco endereco;
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos;
	
	@JsonIgnore
	@ManyToMany //POR PADRÃO A ESTRATÉGIA DE CONSULTA É LAZY
	@JoinTable(name = "restaurante_forma_pagamento",
			   joinColumns = @JoinColumn(name = "restaurante_id"),
			   inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private List<FormaPagamento> formasPagamento;
	
	@JsonIgnore
	@CreationTimestamp //ATRIBUI A DATA ATUAL NO MOMENTO DA CRIAÇÃO DE UMA ENTIDADE NA BASE
	@Column(nullable = false)
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	@UpdateTimestamp //ATRIBUI A DATA ATUAL NO MOMENTO DA ATUALIZAÇÃO DE UMA ENTIDADE NA BASE
	@Column(nullable = false)
	private LocalDateTime dataAtualizacao;

}
