package com.br.Agro_Mapping.dto.authentication;

import java.time.LocalDate;
import com.br.Agro_Mapping.model.enuns.UserRole;

public record RegisterDTO(
        String nome,
        String email,
        String senha,
        LocalDate dataDeNascimento,
        UserRole userRole
) {}
