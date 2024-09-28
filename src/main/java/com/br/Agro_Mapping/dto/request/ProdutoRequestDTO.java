package com.br.Agro_Mapping.dto.request;

import java.util.UUID;

public record ProdutoRequestDTO(String nome
        , String categoria, Integer quantidadeDisponivel
        , String descricao
        , double preco, String imagem, UUID usuarioId) {

}
