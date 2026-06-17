package com.br.Agro_Mapping.dto.responses;

import java.time.LocalDate;
import java.util.UUID;

public record ReservaResponseDTO(
        UUID idReserva,
        String status,
        LocalDate dataReserva,
        String qrCodeHash,
        Integer quantidade,
        ProdutoResponseDTO produto,
        FeiraResponseDTO feira
) {}
