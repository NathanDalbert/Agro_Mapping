package com.br.Agro_Mapping.dto.responses;

import java.util.UUID;

public record EstoqueResponseDTO(UUID idEstoque, UUID produtoId, Integer quantidadeDisponivel) {
}
