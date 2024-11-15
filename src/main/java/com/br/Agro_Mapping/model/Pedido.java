package com.br.Agro_Mapping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "pedido")
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_pedido", nullable = false)
    private UUID IdPedido;

    @Column(name = "data_pedido", nullable = false)
    @NotNull(message = "O campo data de pedido é obrigatório")
    private LocalDate dataPedido;

    @Column(name = "valor_total", nullable = false)
    @NotNull(message = "O campo valor total é obrigatório")
    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> Itempedidos = new ArrayList<>();


    private Pedido(LocalDate dataPedido, List<ItemPedido> itemPedidos) {
        this.dataPedido = dataPedido;
        this.Itempedidos = itemPedidos;
        this.valorTotal = calcularValorTotal();
    }


    public static Pedido newPedido(LocalDate dataPedido, List<ItemPedido> itemPedidos) {
        Pedido pedido = new Pedido(dataPedido, itemPedidos);
        itemPedidos.forEach(item -> item.setPedido(pedido));
        return pedido;
    }


    public Double calcularValorTotal() {
        return Itempedidos.stream()
                .mapToDouble(ItemPedido::getValorTotalItem)
                .sum();
    }
}
