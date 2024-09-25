
CREATE TABLE pedido (
    id_pedido UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    data_pedido DATE NOT NULL,
    valor_total NUMERIC(10, 2) NOT NULL,
    id_usuario UUID,
    CONSTRAINT fk_pedido_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);
