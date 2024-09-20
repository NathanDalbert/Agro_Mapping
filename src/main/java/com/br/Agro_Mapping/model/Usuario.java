package com.br.Agro_Mapping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;

    @Column(name = "email", nullable = false)
    @NotBlank(message = "O campo email é obrigatório")
    @Email(message = "O e-mail deve ser válido")
    private String email;

    @Column(name = "senha", nullable = false)
    @NotBlank(message = "O campo senha é obrigatório")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    private String senha;

    @Column(name = "data_de_nascimento", nullable = false)
    @NotNull(message = "O campo data de nascimento é obrigatório")
    private LocalDate dataDeNascimento;
}
