insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Manacá', 5.00, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Burguer Grill', 7.00, 2);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Pastelaricas', 3.00, 2);

insert into estado (id, nome) values (1, 'Ceará');
insert into estado (id, nome) values (2, 'Rio Grande do Norte');
insert into estado (id, nome) values (3, 'Paraíba');
insert into estado (id, nome) values (4, 'Pernambuco');

insert into cidade (id, nome, estado_id) values (1, 'São Lourenço da Mata', 4);
insert into cidade (id, nome, estado_id) values (2, 'Campina Grande', 3);
insert into cidade (id, nome, estado_id) values (3, 'Recife', 4);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (1,3), (2,3), (3,2), (3,3);