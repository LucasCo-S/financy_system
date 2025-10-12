CREATE DATABASE IF NOT EXISTS sistema_bancario;

USE sistema_bancario;

CREATE TABLE cliente (
	id_Cliente INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    CPF VARCHAR(11) NOT NULL UNIQUE,
    data_Nascimento DATE NOT NULL
);

CREATE TABLE conta (
	id_Conta INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    numero_Conta VARCHAR(6) NOT NULL,
    saldo DECIMAL(15,2) DEFAULT 0.0,
    data_Abertura TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_Cliente INT UNSIGNED NOT NULL,
    tipo_conta VARCHAR(25) NOT NULL,
    
    CONSTRAINT fk_ClienteConta FOREIGN KEY (id_Cliente) REFERENCES Cliente (id_Cliente)
    ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE conta_corrente(
	id_conta INT UNSIGNED PRIMARY KEY,
    tarifaMensal DECIMAL(5,2) NOT NULL, 
    
    CONSTRAINT FK_contaCOR FOREIGN KEY (id_conta) REFERENCES conta(id_conta)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE conta_poupanca(
	id_conta INT UNSIGNED PRIMARY KEY,
    dataRendimento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	rendimento DECIMAL(5,2) NOT NULL,
    
    CONSTRAINT FK_contaPOU FOREIGN KEY (id_conta) REFERENCES conta(id_conta)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE conta_investimento(
	id_conta INT UNSIGNED PRIMARY KEY,
    tipoInvestimento VARCHAR(63) NOT NULL,
    valorAplicado DECIMAL(11, 2) NOT NULL,
    
    CONSTRAINT FK_contaINV FOREIGN KEY (id_conta) REFERENCES conta(id_conta)
    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE transacao(
	id_transacao INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    formaPagamento VARCHAR(127) NOT NULL,
    dataPagamento TIMESTAMP NOT NULL,
    valor DECIMAL(13,2) NOT NULL,
    
    id_contaOrg INT UNSIGNED,
    id_contaDest INT UNSIGNED,
    
    CONSTRAINT FK_pagContaOrg FOREIGN KEY (id_contaOrg) REFERENCES conta(id_conta)
    ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT FK_pagContaDest FOREIGN KEY (id_contaDest) REFERENCES conta(id_conta)
    ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE tran_pix(
	id_transacao INT UNSIGNED PRIMARY KEY,
	chaveOrg VARCHAR(255) NOT NULL,
    chaveDest VARCHAR(255) NOT NULL,
    
    CONSTRAINT FK_pagPIX FOREIGN KEY (id_transacao) REFERENCES transacao(id_transacao)
    ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE TABLE tran_boleto(
	id_transacao INT UNSIGNED PRIMARY KEY,
	codBarras VARCHAR(127) NOT NULL,
    dataVencimento DATE NOT NULL,
    
    CONSTRAINT FK_pagBOL FOREIGN KEY (id_transacao) REFERENCES transacao(id_transacao)
    ON UPDATE CASCADE ON DELETE RESTRICT
);