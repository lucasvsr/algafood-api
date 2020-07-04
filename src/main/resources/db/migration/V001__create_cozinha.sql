create table if not exists cozinha
(
	id bigint auto_increment
		primary key,
	nome varchar(60) not null
) engine=InnoDB default charset=utf8;