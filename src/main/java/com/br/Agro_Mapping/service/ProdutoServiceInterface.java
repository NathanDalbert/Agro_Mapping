package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.ProdutoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ProdutoResponseDTO;
import java.util.List;
import java.util.UUID;

public interface ProdutoServiceInterface {
    void criarProduto(ProdutoRequestDTO produtoRequestDTO);
    List<ProdutoResponseDTO> listarProdutos();
    void deletarProduto(UUID id);
    void atualizarProduto(UUID id, ProdutoRequestDTO produtoRequestDTO);

}
