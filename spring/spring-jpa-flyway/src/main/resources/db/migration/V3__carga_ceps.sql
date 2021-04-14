CREATE TABLE schema_one.tab_endereco (
	cep varchar(255) NOT NULL,
	bairro varchar(255) NULL,
	localidade varchar(255) NULL,
	logradouro varchar(255) NULL,
	CONSTRAINT tab_endereco_pkey PRIMARY KEY (cep)
);

INSERT INTO schema_one.tab_endereco (cep, bairro, localidade, logradouro) VALUES('01001000', 'SE', 'SAO PAULO', 'PRACA DA SE');

INSERT INTO schema_one.tab_endereco (cep, bairro, localidade, logradouro) VALUES('03047000', 'BRAS', 'SAO PAULO', 'RUA 21 DE ABRIL');
