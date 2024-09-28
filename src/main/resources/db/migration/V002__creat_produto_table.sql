CREATE TABLE produto (
    id_produto UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    nome VARCHAR(255) NOT NULL CHECK (char_length(nome) > 1),
    categoria VARCHAR(255) NOT NULL,
    quantidade_disponivel INT NOT NULL CHECK (quantidade_disponivel >= 0),
    preco DOUBLE PRECISION NOT NULL CHECK (preco > 0),
    descricao VARCHAR(500) NOT NULL CHECK (char_length(descricao) > 10),
    imagem VARCHAR(500) NOT NULL CHECK (char_length(imagem) > 5),
    id_usuario UUID,
    CONSTRAINT fk_produto_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE

);