package com.br.Agro_Mapping.dto.responses;

import java.util.UUID;

public record ContatoResponseDTO(
        UUID id,
        String telefone
) {}
