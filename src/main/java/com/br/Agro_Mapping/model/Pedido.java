package com.br.Agro_Mapping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
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


    private Pedido(LocalDate dataPedido, Double valorTotal) {
        this.dataPedido = dataPedido;
        this.valorTotal = valorTotal;
    }

    public static Pedido newPedido(LocalDate dataPedido, Double valorTotal) {
        return new Pedido(dataPedido, valorTotal);
    }


}
