package com.br.Agro_Mapping.dto.responses;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record PedidoResponseDTO(UUID idPedido, LocalDate dataPedido , Double valorTotal, String status, UsuarioResponseDTO usuario, List<ItemPedidoResponseDTO> itens

) {}