package com.br.Agro_Mapping.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EstoqueRequestDTO(
        @NotNull(message = "O ID do produto é obrigatório") UUID produtoId,
        @NotNull(message = "A quantidade disponível é obrigatória") @Min(0) Integer quantidadeDisponivel) {
}
