create table if not exists forma_pagamento
(
	id bigint auto_increment
		primary key,
	descricao varchar(80) not null
) engine=InnoDB default charset=utf8;
