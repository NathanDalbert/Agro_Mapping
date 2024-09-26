package com.br.Agro_Mapping.dto.responses;

import java.time.LocalDate;
import java.util.UUID;

public record UsuarioResponseDTO(UUID id, String nome, String email, LocalDate dataDeNascimento) {
}
