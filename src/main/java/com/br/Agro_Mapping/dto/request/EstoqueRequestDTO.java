package com.br.Agro_Mapping.dto.request;

import java.util.UUID;

public record EstoqueRequestDTO(Integer quantidade, UUID idProduto) {
}
