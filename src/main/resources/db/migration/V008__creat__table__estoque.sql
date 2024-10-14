CREATE TABLE estoque (
    id_estoque UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    id_produto UUID NOT NULL,
    quantidade INT NOT NULL CHECK (quantidade >= 0),
    CONSTRAINT fk_estoque_produto FOREIGN KEY (id_produto) REFERENCES produto(id_produto) ON DELETE CASCADE
);
