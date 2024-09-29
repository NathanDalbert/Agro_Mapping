package com.br.Agro_Mapping.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String email) {
        super("O e-mail " + email + " já está registrado.");
    }
}