create table if not exists restaurante_forma_pagamento
(
	restaurante_id bigint not null,
	forma_pagamento_id bigint not null,
	constraint fk_restaurante_forma_pagamento
		foreign key (forma_pagamento_id) references forma_pagamento (id),
	constraint fk_forma_pagamento_restaurante
		foreign key (restaurante_id) references restaurante (id)
) engine=InnoDB default charset=utf8;