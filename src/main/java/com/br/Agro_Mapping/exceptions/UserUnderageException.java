package com.br.Agro_Mapping.exceptions;

import java.time.LocalDate;

public class UserUnderageException extends RuntimeException {
    public UserUnderageException(LocalDate birthDate) {
        super("Usuário com data de nascimento " + birthDate + " é menor de idade.");
    }
}