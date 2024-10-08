package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.ProdutoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ProdutoResponseDTO;
import java.util.List;
import java.util.UUID;

public interface ProdutoServiceInterface {
    ProdutoResponseDTO criarProduto(ProdutoRequestDTO produtoRequestDTO, UUID usuarioId);
    List<ProdutoResponseDTO> listarProdutos();
    void deletarProduto(UUID id);
    ProdutoResponseDTO atualizarProduto(UUID id, ProdutoRequestDTO produtoRequestDTO);
    List<ProdutoResponseDTO> findByName(String name);

}
