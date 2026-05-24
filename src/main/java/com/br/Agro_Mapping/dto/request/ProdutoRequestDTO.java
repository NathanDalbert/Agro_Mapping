package com.br.Agro_Mapping.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ProdutoRequestDTO(
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotBlank(message = "A categoria é obrigatória") String categoria,
        @NotBlank(message = "A descrição é obrigatória") @Size(min = 10, max = 500, message = "A descrição deve ter entre 10 e 500 caracteres") String descricao,
        @NotNull(message = "O preço é obrigatório") @Min(value = 1, message = "O preço deve ser maior que zero") Double preco,
        String imagem,
        UUID usuarioId) {

}
