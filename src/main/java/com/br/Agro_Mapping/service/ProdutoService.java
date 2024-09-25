package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.ProdutoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ProdutoResponseDTO;
import com.br.Agro_Mapping.model.Produto;
import com.br.Agro_Mapping.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProdutoService implements ProdutoServiceInterface {

    private final ProdutoRepository produtoRepository;

    @Override
    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = Produto.newProduto(
                produtoRequestDTO.nome(),
                produtoRequestDTO.categoria(),
                produtoRequestDTO.quantidadeDisponivel(),
                produtoRequestDTO.preco(),
                produtoRequestDTO.descricao(),
                produtoRequestDTO.imagem()
        );

        Produto produtoSalvo = produtoRepository.save(produto);

        return new ProdutoResponseDTO(
                produtoSalvo.getIdProduto(),
                produtoSalvo.getNome(),
                produtoSalvo.getCategoria(),
                produtoSalvo.getPreco(),
                produtoSalvo.getDescricao(),
                produtoSalvo.getQuantidadeDisponivel(),
                produtoSalvo.getImagem()
        );
    }

    @Override
    public List<ProdutoResponseDTO> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(produto -> new ProdutoResponseDTO(
                        produto.getIdProduto(),
                        produto.getNome(),
                        produto.getCategoria(),
                        produto.getPreco(),
                        produto.getDescricao(),
                        produto.getQuantidadeDisponivel(),
                        produto.getImagem()))
                .toList();
    }


    @Override
    public void deletarProduto(UUID id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public ProdutoResponseDTO atualizarProduto(UUID id, ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));

        produto.setNome(produtoRequestDTO.nome());
        produto.setCategoria(produtoRequestDTO.categoria());
        produto.setPreco(produtoRequestDTO.preco());
        produto.setDescricao(produtoRequestDTO.descricao());
        produto.setQuantidadeDisponivel(produtoRequestDTO.quantidadeDisponivel());
        produto.setImagem(produtoRequestDTO.imagem());

        Produto produtoAtualizado = produtoRepository.save(produto);
         return new ProdutoResponseDTO(
                produtoAtualizado.getIdProduto(),
                produtoAtualizado.getNome(),
                produtoAtualizado.getCategoria(),
                produtoAtualizado.getPreco(),
                produtoAtualizado.getDescricao(),
                produtoAtualizado.getQuantidadeDisponivel(),
                produtoAtualizado.getImagem());

    }

    @Override
    public List<ProdutoResponseDTO> findByName(String name) {
        List<Produto> produtos = produtoRepository.findByNome(name);
        return produtos.stream()
                .map(produto -> new ProdutoResponseDTO(
                        produto.getIdProduto(),
                        produto.getNome(),
                        produto.getCategoria(),
                        produto.getPreco(),
                        produto.getDescricao(),
                        produto.getQuantidadeDisponivel(),
                        produto.getImagem()))
                .toList();
    }

}
