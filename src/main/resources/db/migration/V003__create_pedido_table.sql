CREATE TABLE pedido (
    id_pedido UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    data_pedido DATE NOT NULL DEFAULT CURRENT_DATE,
    valor_total NUMERIC(10, 2) NOT NULL CHECK (valor_total >= 0),
    id_usuario UUID,
    CONSTRAINT fk_pedido_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);