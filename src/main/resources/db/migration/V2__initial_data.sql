insert into cliente (criado_em, modificado_em, nome, cpf_cnpj, tipo_pessoa) values
(now(), now(), 'Nielsen Costa Teixeira', '02812648309', 'FISICA'),
(now(), now(), 'UNI7', '12345678000122', 'JURIDICA');

insert into agencia (criado_em, modificado_em, nome, numero, endereco)
values (now(), now(), 'Agencia Top', 1, 'Aldeota');

insert into conta(criado_em, modificado_em, numero, tipo_conta, saldo, agencia_id, cliente_id) values
(now(), now(), 1, 'POUPANCA', 1000, 1, 1),
(now(), now(), 1, 'CORRENTE', 2000, 1, 2);