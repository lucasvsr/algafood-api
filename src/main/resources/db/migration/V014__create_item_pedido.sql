create table item_pedido
(
	id bigint auto_increment,
	quantidade int not null,
	preco_Unitario decimal not null,
	preco_total decimal null,
	observacao varchar(255) null,
	produto_id bigint not null,
	pedido_id bigint not null,
	constraint item_pedido_pk
		primary key (id),
	constraint item_pedido_produto_id_fk
		foreign key (produto_id) references produto (id)
);