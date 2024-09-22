package com.br.Agro_Mapping.dto.responses;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

public record ProdutoResponseDTO(UUID id,String nome, String categoria, double preco, String descricao, Integer quantidadeDisponivel, String imagem) {


}
