package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.EstoqueRequestDTO;
import com.br.Agro_Mapping.dto.responses.EstoqueResponseDTO;
import java.util.List;
import java.util.UUID;

public interface EstoqueServiceInterface {
    EstoqueResponseDTO criarEstoque(EstoqueRequestDTO estoqueRequestDTO, UUID produtoId);
    List<EstoqueResponseDTO> listarEstoques();
    void deletarEstoque(UUID id);
    EstoqueResponseDTO atualizarEstoque(UUID id, EstoqueRequestDTO estoqueRequestDTO);
}
