package com.br.Agro_Mapping.dto.request;
import java.time.LocalDate;

public record PedidoRequestDTO(LocalDate dataPedido
        ,Double valorTotal

) {}