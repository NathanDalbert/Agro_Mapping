package com.br.Agro_Mapping.dto.request;

public record ProdutoRequestDTO(String nome
        ,String categoria, Integer quantidadeDisponivel
        , String descricao
        , double preco, String imagem) {

}
