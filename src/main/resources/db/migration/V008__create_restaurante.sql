create table if not exists restaurante
(
	id bigint auto_increment
		primary key,
	data_atualizacao datetime(6) not null,
	data_cadastro datetime(6) not null,
	endereco_bairro varchar(80) null,
	endereco_cep varchar(20) null,
	endereco_complemento varchar(80) null,
	endereco_logradouro varchar(255) null,
	endereco_numero varchar(20) null,
	nome varchar(255) not null,
	taxa_frete decimal(19,2) not null,
	cozinha_id bigint not null,
	endereco_cidade_id bigint null,
	constraint fk_restaurante_cozinha
		foreign key (cozinha_id) references cozinha (id),
	constraint fk_restaurante_cidade
		foreign key (endereco_cidade_id) references cidade (id)
) engine=InnoDB default charset=utf8;