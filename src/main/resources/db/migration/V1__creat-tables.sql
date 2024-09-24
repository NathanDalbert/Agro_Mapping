CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE produto (
    id_produto UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    quantidade_disponivel INT NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    imagem VARCHAR(255) NOT NULL
);


CREATE TABLE usuario (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    data_de_nascimento DATE NOT NULL
);
CREATE TABLE pedido (
    id_pedido UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    data_pedido DATE NOT NULL,
    valor_total NUMERIC(10, 2) NOT NULL
);
CREATE TABLE item_pedido (
    id_item_pedido UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    preco_unitario NUMERIC(10, 2) NOT NULL,
    quantidade INTEGER NOT NULL
);
CREATE TABLE feira (
    id_feira UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(255) NOT NULL,
    localizacao VARCHAR(255),
    data_funcionamento DATE NOT NULL
);
CREATE TABLE contato (
    id_contato UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    telefone VARCHAR(255) NOT NULL
);

