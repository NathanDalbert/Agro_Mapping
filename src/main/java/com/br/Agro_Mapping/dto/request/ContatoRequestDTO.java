package com.br.Agro_Mapping.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record ContatoRequestDTO(
        @NotBlank(message = "O telefone é obrigatório") String telefone,
        UUID usuarioId
) {}
