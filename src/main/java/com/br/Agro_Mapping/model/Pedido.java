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


    private Pedido(LocalDate dataPedido, Double valorTotal) {
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
    }


    public static Pedido newPedido(LocalDate dataPedido, Double valorTotal) {
        return new Pedido(dataPedido, valorTotal);
    }


    public Double calcularValorTotal() {
        return Itempedidos.stream()
                .mapToDouble(ItemPedido::getValorTotalItem)
                .sum();
    }
}
