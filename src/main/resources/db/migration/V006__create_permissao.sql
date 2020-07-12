create table if not exists permissao
(
	id bigint auto_increment
		primary key,
	descricao varchar(255) not null,
	nome varchar(30) not null
) engine=InnoDB default charset=utf8;