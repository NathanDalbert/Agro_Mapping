package com.br.Agro_Mapping.dto.responses;

import java.time.LocalDate;
import java.util.UUID;

public record ItemPedidoResponseDTO(UUID idPedido, Double precoUnitario, ProdutoResponseDTO produto, Integer quantidade, Double valorTotal


) {}