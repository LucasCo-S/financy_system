USE sistema_bancario;

INSERT INTO cliente (nome, CPF, data_Nascimento) VALUES
('João Pereira', '12345678901', '1985-06-12'),
('Maria Silva', '98765432100', '1992-09-25'),
('Carlos Souza', '45678912300', '1978-01-30');

INSERT INTO conta (numero_Conta, saldo, id_Cliente, tipo_conta) VALUES
('0001-0', 3500.50, 1, 'Corrente'),
('0002-0', 7800.00, 2, 'Poupança'),
('0003-0', 15000.00, 3, 'Investimento');

select * from conta;

INSERT INTO conta_corrente (id_conta, tarifaMensal) VALUES
(1, 25.00);

INSERT INTO conta (numero_Conta, saldo, id_Cliente, tipo_conta) VALUES
('0006-0', 5000.00, 3, 'Poupança'),
('0007-0', 8500.00, 1, 'Poupança');

INSERT INTO conta_poupanca (id_conta, rendimento) VALUES
(2, 0.45);

SELECT cl.nome, cl.cpf, cl.data_nascimento, co.numero_conta, co.saldo, co.data_abertura, co.tipo_conta, i.tipoInvestimento, i.valorAplicado
FROM cliente cl INNER JOIN conta co ON cl.id_cliente = co.id_cliente 
INNER JOIN conta_investimento i ON co.id_conta = i.id_conta WHERE co.id_conta = 3;

INSERT INTO conta (numero_Conta, saldo, id_Cliente, tipo_conta) VALUES
('0008-0', 30000.00, 2, 'Investimento'),
('0009-0', 12000.00, 1, 'Investimento');

INSERT INTO conta_investimento (id_conta, tipoInvestimento, valorAplicado) VALUES
(3, 'Renda Fixa', 10000.00);

INSERT INTO transacao (formaPagamento, dataPagamento, valor, id_contaOrg, id_contaDest) VALUES
('PIX', '2025-10-11 09:15:00', 250.00, 1, 2),
('Boleto', '2025-10-10 15:40:00', 480.00, 2, 1);

INSERT INTO tran_pix (chaveOrg, chaveDest) VALUES
('joao@banco.com', 'maria@banco.com'),
('investidor@banco.com', 'carlos@banco.com'),
('financeiro@banco.com', 'cliente@banco.com');

INSERT INTO tran_boleto (codBarras, dataVencimento) VALUES
('23793812938120381203120381203120381203812038', '2025-10-20'),
('10493812938120381203120381203120381203812038', '2025-11-10'),
('34193812938120381203120381203120381203812038', '2025-12-01');


