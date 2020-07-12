create table if not exists cidade
(
	id bigint auto_increment
		primary key,
	nome varchar(80) not null,
	estado_id bigint null,
	constraint fk_cidade_estado
		foreign key (estado_id) references estado (id)
) engine=InnoDB default charset=utf8;