package com.br.Agro_Mapping.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "feira")
@Entity(name = "feira")
public class Feira {

    private String nome;
    private String Localizacao;
    private LocalDate dataFuncionamento;
}
