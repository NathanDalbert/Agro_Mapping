package com.br.Agro_Mapping.dto.request;

import java.util.UUID;

public record ProdutoRequestDTO(String nome,
                                String categoria,
                                String descricao,
                                double preco,
                                String imagem,
                                UUID usuarioId) {

}
