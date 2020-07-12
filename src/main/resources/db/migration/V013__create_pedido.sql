create table pedido
(
	id bigint auto_increment,
	subtotal decimal not null,
	taxaFrete decimal not null,
	valor_total decimal not null,
	data_criacao timestamp not null,
	data_confirmacao timestamp null,
	data_cancelamento timestamp null,
	data_entrega timestamp null,
	endereco_cep varchar(30) null,
	endereco_logradouro varchar(60) null,
	endereco_numero varchar(30) null,
	endereco_complemento varchar(60) null,
	endereco_bairro varchar(30) null,
	endereco_cidade_id bigint null,
	status_entrega varchar(20) null,
	cliente_id bigint null,
	forma_pagamento bigint null,
	constraint pedido_pk
		primary key (id),
	constraint pedido_forma_pagamento_id_fk
		foreign key (forma_pagamento) references forma_pagamento (id),
	constraint pedido_usuario_id_fk
		foreign key (cliente_id) references usuario (id)
);

