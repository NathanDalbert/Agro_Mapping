package com.br.Agro_Mapping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Ajustado para UUID
    private UUID idProduto;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "Digite um nome para o produto")
    private String nome;

    @Column(name = "categoria", nullable = false)
    @NotBlank(message = "Digite uma categoria para o produto")
    private String categoria;

    @Column(name = "quantidade_disponivel", nullable = false)
    @NotNull(message = "Digite a quantidade disponível do produto")
    private Integer quantidadeDisponivel;

    @Column(name = "preco", nullable = false)
    @NotNull(message = "Digite o preço do produto")
    private Double preco;

    @Column(name = "descricao", nullable = false)
    @NotBlank(message = "Digite uma descrição para o produto")
    @Size(min = 10, max = 500, message = "A descrição deve ter pelo menos 10 caracteres e no máximo 500 caracteres")
    private String descricao;
}
