package com.br.Agro_Mapping.dto.request;

import java.time.LocalDate;

public record UsuarioRequestDTO(String nome, String email, String senha, LocalDate dataNascimento, String userRole) {


}
