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
    @NotBlank(message = "O campo telefone é obrigatório")
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private Contato(String telefone){
        this.telefone = telefone;
    }

    public static Contato newContato(String telefone){
        return new Contato(telefone);
    }

}
