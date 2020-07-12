create table if not exists grupo
(
	id bigint auto_increment
		primary key,
	nome varchar(80) not null
) engine=InnoDB default charset=utf8;