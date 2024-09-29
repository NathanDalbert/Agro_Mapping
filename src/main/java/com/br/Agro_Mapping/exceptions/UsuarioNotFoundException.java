package com.br.Agro_Mapping.exceptions;
import java.util.UUID;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(UUID id) {
        super("Usuário com ID " + id + " não foi encontrado.");
    }
}
