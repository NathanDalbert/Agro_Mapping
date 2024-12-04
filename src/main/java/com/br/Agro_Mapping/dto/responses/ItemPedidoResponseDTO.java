package com.br.Agro_Mapping.dto.responses;

import java.util.UUID;

public record ItemPedidoResponseDTO(
        UUID itemPedido,
        ProdutoResponseDTO produto,
        Integer quantidade,
        Double valorTotalItem,
        UUID idUsuario
) {}
