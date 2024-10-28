package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.ProdutoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ProdutoResponseDTO;
import com.br.Agro_Mapping.model.Usuario;
import com.br.Agro_Mapping.repository.UsuarioRepository;
import com.br.Agro_Mapping.service.mapper.ProdutoMapper;
import com.br.Agro_Mapping.model.Produto;
import com.br.Agro_Mapping.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoService implements ProdutoServiceInterface {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;
    private final UsuarioRepository usuarioRepository;

    @Transactional
    @Override
    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO produtoRequestDTO, UUID usuarioId) {
        Usuario usuario= usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Produto produto = produtoMapper.toProduto(produtoRequestDTO, usuario);
        produto.setUsuario(usuario);
        Produto produtoSalvo = produtoRepository.save(produto);
        return produtoMapper.toProdutoResponseDTO(produtoSalvo);
    }

    @Transactional
    @Override
    public List<ProdutoResponseDTO> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(produtoMapper::toProdutoResponseDTO)
                .toList();
    }

    @Transactional
    @Override
    public void deletarProduto(UUID id) {
        produtoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public ProdutoResponseDTO atualizarProduto(UUID id, ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));

        produto.setNome(produtoRequestDTO.nome());
        produto.setCategoria(produtoRequestDTO.categoria());
        produto.setPreco(produtoRequestDTO.preco());
        produto.setDescricao(produtoRequestDTO.descricao());
        produto.setImagem(produtoRequestDTO.imagem());

        Produto produtoAtualizado = produtoRepository.save(produto);
        return produtoMapper.toProdutoResponseDTO(produtoAtualizado);
    }

    @Transactional
    @Override
    public List<ProdutoResponseDTO> findByName(String name) {
        List<Produto> produtos = produtoRepository.findByNome(name);
        return produtos.stream()
                .map(produtoMapper::toProdutoResponseDTO)
                .toList();
    }
}
