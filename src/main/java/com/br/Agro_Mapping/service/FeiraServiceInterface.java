package com.br.Agro_Mapping.service;


import com.br.Agro_Mapping.dto.request.FeiraRequestDTO;
import com.br.Agro_Mapping.dto.responses.FeiraResponseDTO;

import java.util.List;
import java.util.UUID;

public interface FeiraServiceInterface {
    FeiraResponseDTO criarFeira(FeiraRequestDTO feiraRequestDTO);

    List<FeiraResponseDTO> listarFeiras();

    FeiraResponseDTO buscarFeiraPorId(UUID id);

    FeiraResponseDTO atualizarFeira(UUID id, FeiraRequestDTO feiraRequestDTO);

    void deletarFeira(UUID id);
}
