package com.br.Agro_Mapping.dto.request;
import java.time.LocalDate;
import java.util.UUID;

public record PedidoRequestDTO(LocalDate dataPedido , Double valorTotal, UUID idUsuario

) {}