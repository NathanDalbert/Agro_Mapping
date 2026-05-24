package com.br.Agro_Mapping.exceptions;

import java.util.UUID;

public class EstoqueNotFoundException extends RuntimeException {
    public EstoqueNotFoundException(UUID id) {
        super("Estoque não encontrado com o ID: " + id);
    }
}
