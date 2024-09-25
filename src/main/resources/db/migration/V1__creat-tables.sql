CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE produto (
    id_produto UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(255) NOT NULL,
    quantidade_disponivel INT NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    imagem VARCHAR(500) NOT NULL
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
    valor_total NUMERIC(10, 2) NOT NULL,
    id_usuario UUID,
    CONSTRAINT fk_pedido_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE item_pedido (
    id_item_pedido UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    preco_unitario NUMERIC(10, 2) NOT NULL,
    quantidade INTEGER NOT NULL,
    id_pedido UUID,
    id_produto UUID,
    CONSTRAINT fk_item_pedido_pedido FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido),
    CONSTRAINT fk_item_pedido_produto FOREIGN KEY (id_produto) REFERENCES produto(id_produto)
);

CREATE TABLE feira (
    id_feira UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(255) NOT NULL,
    localizacao VARCHAR(255),
    data_funcionamento DATE NOT NULL
);

CREATE TABLE contato (
    id_contato UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    telefone VARCHAR(255) NOT NULL,
    id_usuario UUID,
    CONSTRAINT fk_contato_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

ALTER TABLE produto
ADD COLUMN id_feira UUID,
ADD CONSTRAINT fk_produto_feira FOREIGN KEY (id_feira) REFERENCES feira(id_feira);
