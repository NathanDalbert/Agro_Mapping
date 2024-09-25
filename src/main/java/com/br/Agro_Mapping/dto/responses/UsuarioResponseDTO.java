package com.br.Agro_Mapping.dto.responses;

import java.util.UUID;

public record UsuarioResponseDTO(UUID id, String nome, String email, java.time.LocalDate dataDeNascimento) {
}
