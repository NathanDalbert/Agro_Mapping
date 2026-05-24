package com.br.Agro_Mapping.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ItemPedidoRequestDTO(
        @NotNull(message = "O ID do produto é obrigatório") UUID idProduto,
        @NotNull(message = "A quantidade é obrigatória") @Min(1) Integer quantidade,
        UUID idUsuario) {}
