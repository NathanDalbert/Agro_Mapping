package com.br.Agro_Mapping.model;

import com.br.Agro_Mapping.model.enuns.ReservaStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "reserva")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_reserva")
    private UUID idReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_feira")
    private Feira feira;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReservaStatus status = ReservaStatus.PENDENTE;

    @Column(name = "data_reserva", nullable = false)
    private LocalDate dataReserva;

    @Column(name = "qr_code_hash", nullable = false, unique = true)
    private String qrCodeHash;
}
