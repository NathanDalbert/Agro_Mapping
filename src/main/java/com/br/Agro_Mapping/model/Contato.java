package com.br.Agro_Mapping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "contato")
@Table(name = "contato")

public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_contato", nullable = false)
    private UUID id;
    @Column(name = "email", nullable = false)
    @NotBlank(message = "O campo email é obrigatório")
    private String telefone;

}
