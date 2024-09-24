package com.br.Agro_Mapping.dto.responses;

import java.time.LocalDate;
import java.util.UUID;

public record FeiraResponseDTO(UUID idFeira, String nome, String localizacao, LocalDate dataFuncionamento) {
}
