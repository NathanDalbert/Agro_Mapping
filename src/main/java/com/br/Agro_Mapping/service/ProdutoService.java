package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.ProdutoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ProdutoResponseDTO;

import java.util.List;
import java.util.UUID;


public class ProdutoService  implements ProdutoServiceInterface{

    @Override
    public void criarProduto(ProdutoRequestDTO produtoRequestDTO) {

    }

    @Override
    public List<ProdutoResponseDTO> listarProdutos() {
        return List.of();
    }

    @Override
    public ProdutoResponseDTO listarProdutoPorId(UUID id) {
        return null;
    }

    @Override
    public void deletarProduto(UUID id) {

    }

    @Override
    public void atualizarProduto(UUID id, ProdutoRequestDTO produtoRequestDTO) {

    }
}
