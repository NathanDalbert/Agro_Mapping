package com.br.Agro_Mapping.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record PedidoRequestDTO(
        @NotNull(message = "A data do pedido é obrigatória") LocalDate dataPedido,
        @NotNull(message = "O ID do usuário é obrigatório") UUID idUsuario,
        @NotEmpty(message = "A lista de itens não pode estar vazia") @Valid List<ItemPedidoRequestDTO> itens
) {}
