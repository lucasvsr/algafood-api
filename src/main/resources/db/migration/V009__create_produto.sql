create table if not exists produto
(
	id bigint auto_increment
		primary key,
	ativo bit null,
	descricao varchar(255) not null,
	nome varchar(80) not null,
	preco decimal(19,2) null,
	restaurante_id bigint null,
	constraint fk_produto_restaurante
		foreign key (restaurante_id) references restaurante (id)
) engine=InnoDB default charset=utf8;