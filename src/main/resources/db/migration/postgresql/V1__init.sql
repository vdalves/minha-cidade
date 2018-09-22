create table orgao(
    id serial,
    nome varchar,
    codigo varchar,
    primary key(id)
);

create table pessoa(
    id serial,
    nome varchar,
    sobrenome varchar,
	cpf varchar,
	email varchar,
	dt_nascimento date,
	orgao integer,
    primary key(id),
    foreign key (orgao) references orgao(id)
);

create table usuario(
    id serial,
    usuario varchar,
    senha varchar,
    tipo integer,
    pessoa integer,
    primary key(id),
    foreign key (pessoa) references pessoa(id)
);