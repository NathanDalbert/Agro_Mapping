package com.br.Agro_Mapping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario  {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

    private Usuario(String nome, String email, String senha, LocalDate dataDeNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataDeNascimento = dataDeNascimento;
    }

    public static Usuario newUsuario(String nome, String email, String senha, LocalDate dataDeNascimento) {
        return new Usuario(nome, email, senha, dataDeNascimento);
    }

}
