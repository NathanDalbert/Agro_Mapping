package com.br.Agro_Mapping.dto.request;

import java.time.LocalDate;

public record FeiraRequestDTO(String nome, String localizacao, LocalDate dataFuncionamento) {
}
