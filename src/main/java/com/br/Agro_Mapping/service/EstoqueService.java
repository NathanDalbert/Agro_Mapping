package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.EstoqueRequestDTO;
import com.br.Agro_Mapping.dto.responses.EstoqueResponseDTO;
import com.br.Agro_Mapping.model.Estoque;
import com.br.Agro_Mapping.model.Produto;
import com.br.Agro_Mapping.repository.EstoqueRepository;
import com.br.Agro_Mapping.repository.ProdutoRepository;
import com.br.Agro_Mapping.service.mapper.EstoqueMapper;
import jakarta.transaction.Transactional;
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

    @Transactional
    @Override
    public EstoqueResponseDTO criarEstoque(EstoqueRequestDTO estoqueRequestDTO, UUID produtoId) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Estoque estoque = estoqueMapper.toEstoque(estoqueRequestDTO, produto);
        Estoque estoqueSalvo = estoqueRepository.save(estoque);
        return estoqueMapper.toEstoqueResponseDTO(estoqueSalvo);
    }

    @Transactional
    @Override
    public List<EstoqueResponseDTO> listarEstoques() {
        List<Estoque> estoques = estoqueRepository.findAll();
        return estoques.stream()
                .map(estoqueMapper::toEstoqueResponseDTO)
                .toList();
    }

    @Transactional
    @Override
    public void deletarEstoque(UUID id) {
        estoqueRepository.deleteById(id);
    }

    @Transactional
    @Override
    public EstoqueResponseDTO atualizarEstoque(UUID id, EstoqueRequestDTO estoqueRequestDTO) {
        Estoque estoque = estoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado com o ID: " + id));

        estoque.setQuantidadeDisponivel(estoqueRequestDTO.quantidadeDisponivel());
        Estoque estoqueAtualizado = estoqueRepository.save(estoque);
        return estoqueMapper.toEstoqueResponseDTO(estoqueAtualizado);
    }
}
