package com.br.Agro_Mapping.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FeiraRequestDTO(
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotBlank(message = "A localização é obrigatória") String localizacao,
        @NotNull(message = "A data de funcionamento é obrigatória") LocalDate dataFuncionamento,
        Double latitude,
        Double longitude) {
}
