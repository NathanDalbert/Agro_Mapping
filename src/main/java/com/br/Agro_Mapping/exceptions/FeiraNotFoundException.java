package com.br.Agro_Mapping.exceptions;

import java.util.UUID;

public class FeiraNotFoundException extends RuntimeException {
    public FeiraNotFoundException(UUID id) {
        super("Feira não encontrada com o ID: " + id);
    }
}
