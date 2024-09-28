package com.br.Agro_Mapping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "produto")
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_produto")
    private UUID idProduto;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "Digite um nome para o produto")
    private String nome;

    @Column(name = "categoria", nullable = false)
    @NotBlank(message = "Digite uma categoria para o produto")
    private String categoria;

    @Column(name = "quantidade_disponivel", nullable = false)
    @NotNull(message = "Digite a quantidade disponível do produto")
    @Min(value = 1, message = "A quantidade disponível deve ser maior que zero")
    private Integer quantidadeDisponivel;

    @Column(name = "preco", nullable = false)
    @NotNull(message = "Digite o preço do produto")
    @Min(value =1, message = "O preço deve ser maior que zero")
    private Double preco;

    @Column(name = "descricao", nullable = false)
    @NotBlank(message = "Digite uma descrição para o produto")
    @Size(min = 10, max = 500, message = "A descrição deve ter pelo menos 10 caracteres e no máximo 500 caracteres")
    private String descricao;

    @Column(name = "imagem", nullable = false)
    @Size (max = 500, message = "O link deve ter no máximo 500 caracteres")
    @NotBlank(message = "Adicione uma imagem a o produto")
    private String imagem;

    @ManyToOne
    @JoinColumn(name = "id_feira")
    private Feira feira;

    @OneToMany(mappedBy = "produto")
    private List<ItemPedido> itensPedido;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private Produto(String nome, String categoria, Integer quantidadeDisponivel, Double preco, String descricao, String imagem) {
        this.nome = nome;
        this.categoria = categoria;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.preco = preco;
        this.descricao = descricao;
        this.imagem = imagem;
    }


    public static Produto newProduto(String nome, String categoria, Integer quantidadeDisponivel, Double preco, String descricao, String imagem) {
        if (quantidadeDisponivel <= 0) {
            throw new IllegalArgumentException("A quantidade disponível deve ser maior que zero.");
        }
        if (preco < 0) {
            throw new IllegalArgumentException("O preço deve ser maior ou igual a zero.");
        }
        return new Produto(nome, categoria, quantidadeDisponivel, preco, descricao, imagem);
    }

}
