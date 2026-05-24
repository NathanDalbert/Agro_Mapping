package com.br.Agro_Mapping.exceptions;

import java.util.UUID;

public class ProdutoNotFoundException extends RuntimeException {
    public ProdutoNotFoundException(UUID id) {
        super("Produto não encontrado com o ID: " + id);
    }
}
