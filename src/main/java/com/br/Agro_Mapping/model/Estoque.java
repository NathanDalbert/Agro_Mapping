package com.br.Agro_Mapping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estoque")
@Entity
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_estoque")
    private UUID idEstoque;

    @Column(name = "quantidade_disponivel", nullable = false)
    @NotNull(message = "Digite a quantidade disponível do produto")
    @Min(value = 1, message = "A quantidade disponível deve ser maior que zero")
    private Integer quantidadeDisponivel;

    @OneToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

}
