create table if not exists usuario
(
	id bigint auto_increment
		primary key,
	data_cadastro datetime(6) not null,
	email varchar(80) not null,
	nome varchar(80) not null,
	senha varchar(255) not null
) engine=InnoDB default charset=utf8;
