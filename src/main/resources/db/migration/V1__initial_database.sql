CREATE TYPE tipo_pessoa AS ENUM (
    'FISICA',
    'JURIDICA'
);

CREATE TYPE tipo_conta AS ENUM (
    'CORRENTE',
    'POUPANCA'
);

CREATE TYPE tipo_movimentacao AS ENUM (
    'SAQUE',
    'DEPOSITO',
    'TRANSFERENCIA'
);

CREATE TABLE cliente
(
   id SERIAL PRIMARY KEY,
   criado_em timestamp with time zone NOT NULL,
   modificado_em timestamp with time zone NOT NULL,
   nome VARCHAR(100) NOT NULL,
   cpf_cnpj varchar (14),
   tipo_pessoa tipo_pessoa NOT NULL
);

CREATE TABLE agencia
(
   id SERIAL PRIMARY KEY,
   criado_em timestamp with time zone NOT NULL,
   modificado_em timestamp with time zone NOT NULL,
   nome VARCHAR(100) NOT NULL,
   numero integer NOT NULL,
   endereco VARCHAR(100) NOT NULL
);

CREATE TABLE conta
(
   id SERIAL PRIMARY KEY,
   criado_em timestamp with time zone NOT NULL,
   modificado_em timestamp with time zone NOT NULL,
   numero integer NOT NULL,
   tipo_conta tipo_conta NOT NULL,
   saldo float NOT NULL,
   agencia_id integer references agencia (id),
   cliente_id integer references conta (id)
);

CREATE TABLE movimentacao
(
   id SERIAL PRIMARY KEY,
   criado_em timestamp with time zone NOT NULL,
   modificado_em timestamp with time zone NOT NULL,
   tipo_movimentacao tipo_movimentacao NOT NULL,
   valor float NOT NULL,
   saldo float NOT NULL,
   conta_origem_id integer references conta (id),
   conta_destino_id integer references conta (id)
);