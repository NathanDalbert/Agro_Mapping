CREATE TABLE feira (
    id_feira UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(255) NOT NULL CHECK (char_length(nome) > 1),
    localizacao VARCHAR(255),
    data_funcionamento DATE NOT NULL CHECK (data_funcionamento >= CURRENT_DATE)
)
;
