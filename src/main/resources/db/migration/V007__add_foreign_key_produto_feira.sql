ALTER TABLE produto
ADD COLUMN id_feira UUID,
ADD CONSTRAINT fk_produto_feira FOREIGN KEY (id_feira) REFERENCES feira(id_feira);
