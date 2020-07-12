create table if not exists usuario_grupo
(
	usuario_id bigint not null,
	grupo_id bigint not null,
	constraint fk_usuario_grupo
		foreign key (grupo_id) references grupo (id),
	constraint fk_grupo_usuario
		foreign key (usuario_id) references usuario (id)
) engine=InnoDB default charset=utf8;