package com.br.Agro_Mapping.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
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
    private UUID idUsuario;

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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "data_de_nascimento", nullable = false)
    @NotNull(message = "O campo data de nascimento é obrigatório")
    private LocalDate dataDeNascimento;

    @OneToMany(mappedBy = "usuario")
    private List<Contato> contatos;

    @OneToMany(mappedBy = "usuario")
    private List<Pedido> pedidos;

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
