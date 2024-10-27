package com.br.Agro_Mapping.model;

import com.br.Agro_Mapping.model.enuns.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "usuario")
@Table(name = "usuario")
public class Usuario  implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_usuario", nullable = false)
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

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Contato> contatos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<Produto> produtos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    public Usuario( String email, String senha, UserRole userRole) {
        this.email = email;
        this.senha = senha;
        this.userRole = userRole;
    }
    private Usuario(String nome, String email, String senha, LocalDate dataDeNascimento) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataDeNascimento = dataDeNascimento;


    }

    public static Usuario newUsuario(String nome, String email, String senha, LocalDate dataDeNascimento) {
        return new Usuario(nome, email, senha, dataDeNascimento);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.userRole == userRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_SELLER"));
        }
        else if (this.userRole == userRole.SELLER) {
            return List.of(new SimpleGrantedAuthority("ROLE_SELLER"), new SimpleGrantedAuthority("ROLE_USER"));
        }
        else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
