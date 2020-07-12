create table if not exists grupo_permissao
(
	grupo_id bigint not null,
	permissao_id bigint not null,
	constraint fk_permissa_grupo
		foreign key (permissao_id) references permissao (id),
	constraint fk_grupo_permissao
		foreign key (grupo_id) references grupo (id)
) engine=InnoDB default charset=utf8;