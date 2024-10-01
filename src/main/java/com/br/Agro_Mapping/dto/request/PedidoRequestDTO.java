package com.br.Agro_Mapping.dto.request;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record PedidoRequestDTO(LocalDate dataPedido , Double valorTotal, UUID idUsuario,  List<ItemPedidoRequestDTO> itens, UUID IdUsuario
) {}