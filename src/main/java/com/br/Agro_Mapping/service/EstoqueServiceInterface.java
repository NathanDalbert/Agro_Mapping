package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.EstoqueRequestDTO;
import com.br.Agro_Mapping.dto.responses.EstoqueResponseDTO;

import java.util.List;
import java.util.UUID;

public interface EstoqueServiceInterface {

    EstoqueResponseDTO criarEstoque(EstoqueRequestDTO estoqueRequestDTO);

    List<EstoqueResponseDTO> listarEstoques();

    EstoqueResponseDTO buscarEstoquePorId(UUID id);

    EstoqueResponseDTO atualizarEstoque(UUID id, EstoqueRequestDTO estoqueRequestDTO);

    void deletarEstoque(UUID id);
}
