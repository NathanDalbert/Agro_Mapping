package com.br.Agro_Mapping.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReservaRequestDTO(
        @NotNull(message = "O ID do usuário é obrigatório") UUID idUsuario,
        @NotNull(message = "O ID do produto é obrigatório") UUID idProduto,
        @NotNull(message = "A quantidade é obrigatória") @Min(1) Integer quantidade
) {}
