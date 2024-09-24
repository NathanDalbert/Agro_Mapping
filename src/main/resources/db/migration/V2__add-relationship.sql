ALTER TABLE item_pedido
ADD COLUMN id_pedido UUID,
ADD CONSTRAINT fk_item_pedido_pedido FOREIGN KEY (id_pedido) REFERENCES pedido(id_pedido);

ALTER TABLE pedido
ADD COLUMN id_usuario UUID,
ADD CONSTRAINT fk_pedido_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id);

ALTER TABLE item_pedido
ADD COLUMN id_produto UUID,
ADD CONSTRAINT fk_item_pedido_produto FOREIGN KEY (id_produto) REFERENCES produto(id_produto);

ALTER TABLE produto
ADD COLUMN id_feira UUID,
ADD CONSTRAINT fk_produto_feira FOREIGN KEY (id_feira) REFERENCES feira(id_feira);

ALTER TABLE contato
ADD COLUMN id_usuario UUID,
ADD CONSTRAINT fk_contato_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id);
