ALTER TABLE produto
ADD COLUMN id_estoque UUID REFERENCES estoque(id_estoque);
