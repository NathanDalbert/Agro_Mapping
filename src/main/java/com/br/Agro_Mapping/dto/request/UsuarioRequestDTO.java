package com.br.Agro_Mapping.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UsuarioRequestDTO(
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotBlank(message = "O email é obrigatório") @Email(message = "Email inválido") String email,
        @NotBlank(message = "A senha é obrigatória") @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres") String senha,
        @NotNull(message = "A data de nascimento é obrigatória") LocalDate dataNascimento,
        String userRole) {
}
