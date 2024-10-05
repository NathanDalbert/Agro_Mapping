package com.br.Agro_Mapping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_estoque")
    private UUID idEstoque;


    @NotNull(message = "Digite a quantidade do produto")
    @Min(value = 1, message = "A quantidade deve ser maior que zero")
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @OneToMany(mappedBy = "estoque")
    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
    private Produto produto;

}
