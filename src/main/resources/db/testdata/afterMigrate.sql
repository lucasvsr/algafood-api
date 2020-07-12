set foreign_key_checks = 0;

delete from grupo_permissao;

delete from grupo;

delete from permissao;

delete from produto;

delete from restaurante_forma_pagamento;

delete from forma_pagamento;

delete from restaurante;

delete from cidade;

delete from cozinha;

delete from estado;

delete from usuario;

set foreign_key_checks = 1;

alter table grupo auto_increment = 1;

alter table permissao auto_increment = 1;

alter table produto auto_increment = 1;

alter table forma_pagamento auto_increment = 1;

alter table restaurante auto_increment = 1;

alter table cidade auto_increment = 1;

alter table cozinha auto_increment = 1;

alter table estado auto_increment = 1;

alter table usuario auto_increment = 1;

insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into estado (id, nome) values (1, 'Ceará');
insert into estado (id, nome) values (2, 'Rio Grande do Norte');
insert into estado (id, nome) values (3, 'Paraíba');
insert into estado (id, nome) values (4, 'Pernambuco');

insert into cidade (id, nome, estado_id) values (1, 'São Lourenço da Mata', 4);
insert into cidade (id, nome, estado_id) values (2, 'Campina Grande', 3);
insert into cidade (id, nome, estado_id) values (3, 'Recife', 4);

INSERT INTO algafood.restaurante (id, data_atualizacao, data_cadastro, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id) VALUES (1, utc_timestamp, utc_timestamp,'Vila do Reinado', '54737-150', 'Nº 8', 'Av. Dr. Francisco Correia', '81 35250854', 'Manacá', 5.00, 1, 1);
INSERT INTO algafood.restaurante (id, data_atualizacao, data_cadastro, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id) VALUES (2, utc_timestamp, utc_timestamp, 'Recife', '14528-150', 'S/N', 'Av. Polidoro', '81 30056987', 'Burguer Grill', 7.00, 2, 3);
INSERT INTO algafood.restaurante (id, data_atualizacao, data_cadastro, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, nome, taxa_frete, cozinha_id, endereco_cidade_id) VALUES (3, utc_timestamp, utc_timestamp, 'Chã da Tábua', '59874-150', '85', 'Av. Dr. Correia', '81 99957482', 'Pastelaricas', 3.00, 2, 1);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (1,3), (2,3), (3,2), (3,3);