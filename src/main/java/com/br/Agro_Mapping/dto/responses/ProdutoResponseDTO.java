package com.br.Agro_Mapping.dto.responses;

import java.util.UUID;

public record ProdutoResponseDTO(UUID id,
                                 String nome,
                                 String categoria,
                                 double preco,
                                 String descricao,
                                 String imagem,
                                 EstoqueResponseDTO estoque) {

}
