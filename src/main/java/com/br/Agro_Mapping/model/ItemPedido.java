package com.br.Agro_Mapping.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "item_pedido")
@Table(name = "item_pedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_item_pedido", nullable = false)
    private UUID idItemPedido;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public double getValorTotalItem() {
        return produto.getPreco() * quantidade;
    }

    private ItemPedido(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public static ItemPedido newItemPedido(Integer quantidade) {
        return new ItemPedido(quantidade);
    }
}
