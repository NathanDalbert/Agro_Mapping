package com.br.Agro_Mapping.dto.responses;
import java.time.LocalDate;
import java.util.UUID;

public record PedidoResponseDTO(UUID idPedido, LocalDate dataPedido, Double valorTotal

) {}