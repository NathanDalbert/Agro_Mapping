package com.br.Agro_Mapping.dto.request;

import java.util.UUID;

public record ItemPedidoRequestDTO(UUID idProduto
        , Integer quantidade

) {}