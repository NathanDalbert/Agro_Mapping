CREATE TABLE estoque (
    id_estoque UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    id_produto UUID NOT NULL,
    quantidade INTEGER NOT NULL CHECK (quantidade >= 0),
    FOREIGN KEY (id_produto) REFERENCES produto (id_produto) ON DELETE CASCADE
);
