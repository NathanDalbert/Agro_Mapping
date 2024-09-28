CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE usuario (
    id_usuario UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    nome VARCHAR(255) NOT NULL CHECK (char_length(nome) > 1),
    email VARCHAR(255) NOT NULL UNIQUE CHECK (email ~* '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+[A-Za-z]{2,}$'),
    senha VARCHAR(255) NOT NULL CHECK (char_length(senha) >= 8),
    data_de_nascimento DATE NOT NULL CHECK (data_de_nascimento <= CURRENT_DATE - INTERVAL '18 years')
);