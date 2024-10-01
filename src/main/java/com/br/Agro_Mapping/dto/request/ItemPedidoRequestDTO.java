package com.br.Agro_Mapping.dto.request;

import java.time.LocalDate;
import java.util.UUID;

public record ItemPedidoRequestDTO(UUID idProduto,Double precoUnitario
        , Integer quantidade

) {}