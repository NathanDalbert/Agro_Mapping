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
