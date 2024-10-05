package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.EstoqueRequestDTO;
import com.br.Agro_Mapping.dto.responses.EstoqueResponseDTO;
import com.br.Agro_Mapping.model.Estoque;
import com.br.Agro_Mapping.model.Produto;
import com.br.Agro_Mapping.repository.EstoqueRepository;
import com.br.Agro_Mapping.repository.ProdutoRepository;
import com.br.Agro_Mapping.service.mapper.EstoqueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EstoqueService implements EstoqueServiceInterface {

    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;
    private final EstoqueMapper estoqueMapper;

    @Override
    public EstoqueResponseDTO criarEstoque(EstoqueRequestDTO estoqueRequestDTO) {
        Produto produto = produtoRepository.findById(estoqueRequestDTO.idProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Estoque estoque = estoqueMapper.toEstoque(estoqueRequestDTO, produto);
        Estoque estoqueSalvo = estoqueRepository.save(estoque);
        return estoqueMapper.toEstoqueResponseDTO(estoqueSalvo);
    }

    @Override
    public List<EstoqueResponseDTO> listarEstoques() {
        return estoqueRepository.findAll().stream()
                .map(estoqueMapper::toEstoqueResponseDTO)
                .toList();
    }

    @Override
    public EstoqueResponseDTO buscarEstoquePorId(UUID id) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
        return estoqueMapper.toEstoqueResponseDTO(estoque);
    }

    @Override
    public EstoqueResponseDTO atualizarEstoque(UUID id, EstoqueRequestDTO estoqueRequestDTO) {
        Produto produto = produtoRepository.findById(estoqueRequestDTO.idProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        estoque.setQuantidade(estoqueRequestDTO.quantidade());

        estoque.setProdutos(List.of(produto));


        Estoque estoqueAtualizado = estoqueRepository.save(estoque);
        return estoqueMapper.toEstoqueResponseDTO(estoqueAtualizado);
    }

    @Override
    public void deletarEstoque(UUID id) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));

        estoqueRepository.delete(estoque);
    }
}
