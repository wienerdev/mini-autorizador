USE miniautorizador;

CREATE TABLE CARTAO (
	id integer auto_increment not null,
    numero varchar(16) not null unique,
    senha varchar(4) not null,
    saldo double not null,
    primary key (id)
);