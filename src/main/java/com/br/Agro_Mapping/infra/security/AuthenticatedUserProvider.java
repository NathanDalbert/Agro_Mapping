package com.br.Agro_Mapping.infra.security;

import com.br.Agro_Mapping.model.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AuthenticatedUserProvider {

    public Usuario getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof Usuario)) {
            throw new RuntimeException("Usuário não autenticado.");
        }
        return (Usuario) authentication.getPrincipal();
    }

    public UUID getAuthenticatedUserId() {
        return getAuthenticatedUser().getIdUsuario();
    }
}
