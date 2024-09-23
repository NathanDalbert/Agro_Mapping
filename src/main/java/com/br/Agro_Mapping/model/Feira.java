package com.br.Agro_Mapping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "feira")
@Entity(name = "feira")
public class Feira {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_feira", nullable = false)
    private UUID idFeira;

    @Column(name = "nome", nullable = false)
    @NotBlank(message = "O nome da feira  é obrigatório")
    private String nome;

    private String Localizacao;

    @Column(name = "data_funcionamento", nullable = false)
    @NotNull(message = "A data de funcionamento é obrigatória")
    private LocalDate dataFuncionamento;

    private Feira(String nome, String Localizacao, LocalDate dataFuncionamento) {
        this.nome = nome;
        this.Localizacao = Localizacao;
        this.dataFuncionamento = dataFuncionamento;
    }

    public static Feira newFeira(String nome, String Localizacao, LocalDate dataFuncionamento) {
        return new Feira(nome, Localizacao, dataFuncionamento);
    }
}
