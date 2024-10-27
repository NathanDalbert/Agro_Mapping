CREATE TABLE estoque (
    id_estoque UUID PRIMARY KEY DEFAULT gen_random_uuid(), -- Utilizando UUID como chave primária
    id_produto UUID NOT NULL, -- Mudando para UUID para corresponder ao tipo da entidade
    quantidade_disponivel INT NOT NULL CHECK (quantidade_disponivel > 0), -- Alterando o nome da coluna e adicionando uma verificação
    FOREIGN KEY (id_produto) REFERENCES produto(idProduto) -- Mantendo a referência correta
);
