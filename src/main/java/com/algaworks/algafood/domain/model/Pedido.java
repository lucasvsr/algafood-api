package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.algaworks.algafood.domain.model.enums.StatusPagamentoEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	@Column(nullable = false)  
	private BigDecimal subtotal;
	
	@Column(nullable = false)
	private BigDecimal taxaFrete;
	
	@Column(nullable = false)
	private BigDecimal valorTotal;
	
	@Column(nullable = false)
	private LocalDateTime dataCriacao;
	
	private LocalDateTime dataConfirmacao;
	
	private LocalDateTime dataCancelamento;
	
	private LocalDateTime dataEntrega;
	
	@Embedded
	private Endereco enderecoEntrega;
	
	private StatusPagamentoEnum statusPagamento;
	
	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Usuario cliente;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pedido")
	private List<ItemPedido> itens;
	
	@OneToOne
	@JoinColumn(name = "forma_pagamento")
	private FormaPagamento formaPagamento;
	

}
