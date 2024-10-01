package com.br.Agro_Mapping.exceptions;
import java.util.UUID;

public class ContatoNotFoundException extends RuntimeException {
    public ContatoNotFoundException(UUID id) {
        super("Contato com ID " + id + " não foi encontrado.");
    }
}
