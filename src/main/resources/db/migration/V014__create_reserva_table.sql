CREATE TABLE reserva (
    id_reserva    UUID         PRIMARY KEY DEFAULT uuid_generate_v4(),
    id_usuario    UUID         NOT NULL,
    id_produto    UUID         NOT NULL,
    id_feira      UUID,
    quantidade    INTEGER      NOT NULL CHECK (quantidade > 0),
    status        VARCHAR(20)  NOT NULL DEFAULT 'PENDENTE',
    data_reserva  DATE         NOT NULL DEFAULT CURRENT_DATE,
    qr_code_hash  VARCHAR(255) NOT NULL UNIQUE,
    CONSTRAINT fk_reserva_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) ON DELETE CASCADE,
    CONSTRAINT fk_reserva_produto FOREIGN KEY (id_produto) REFERENCES produto(id_produto) ON DELETE CASCADE,
    CONSTRAINT fk_reserva_feira   FOREIGN KEY (id_feira)   REFERENCES feira(id_feira)     ON DELETE SET NULL
);
