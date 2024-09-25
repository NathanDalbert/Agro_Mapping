CREATE TABLE contato (
    id_contato UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    telefone VARCHAR(255) NOT NULL,
    id_usuario UUID,
    CONSTRAINT fk_contato_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

