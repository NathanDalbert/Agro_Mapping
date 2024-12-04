ALTER TABLE item_pedido
ADD COLUMN id_usuario UUID,
ADD CONSTRAINT fk_item_pedido_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario);
