package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.ItemPedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ItemPedidoResponseDTO;
import com.br.Agro_Mapping.model.ItemPedido;
import com.br.Agro_Mapping.model.Produto;
import com.br.Agro_Mapping.model.Usuario;  // Importando a classe Usuario
import com.br.Agro_Mapping.repository.ItemPedidoRepository;
import com.br.Agro_Mapping.repository.ProdutoRepository;
import com.br.Agro_Mapping.repository.UsuarioRepository;  // Importando o repositório de Usuario
import com.br.Agro_Mapping.service.mapper.ItemPedidoMapper;
import com.br.Agro_Mapping.service.mapper.ProdutoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemPedidoService implements ItemPedidoServiceInterface {

    private final ItemPedidoRepository itemPedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;  // Repositório de Usuario
    private final ItemPedidoMapper itemPedidoMapper;
    private final ProdutoMapper produtoMapper;

    @Transactional
    @Override
    public ItemPedidoResponseDTO criarItemPedido(ItemPedidoRequestDTO itemPedidoRequestDTO) {

        Produto produto = produtoRepository.findById(itemPedidoRequestDTO.idProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + itemPedidoRequestDTO.idProduto()));

        Usuario usuario = usuarioRepository.findById(itemPedidoRequestDTO.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + itemPedidoRequestDTO.idUsuario()));  // Recuperando o Usuario

        ItemPedido itemPedido = itemPedidoMapper.toItemPedido(itemPedidoRequestDTO, produto, usuario);  // Passando o usuario para o mapper
        ItemPedido itemPedidoSalvo = itemPedidoRepository.save(itemPedido);

        return itemPedidoMapper.toItemPedidoResponseDTO(itemPedidoSalvo, produtoMapper.toProdutoResponseDTO(produto), usuario.getIdUsuario());  // Passando o idUsuario
    }

    @Transactional
    @Override
    public List<ItemPedidoResponseDTO> listaItemPedidos() {
        List<ItemPedido> itemPedidos = itemPedidoRepository.findAll();
        return itemPedidos.stream()
                .map(item -> {
                    Produto produto = item.getProduto();
                    return itemPedidoMapper.toItemPedidoResponseDTO(item, produtoMapper.toProdutoResponseDTO(produto), item.getUsuario().getIdUsuario());  // Passando o idUsuario
                })
                .toList();
    }

    @Transactional
    @Override
    public void deletarItemPedido(UUID id) {
        itemPedidoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public ItemPedidoResponseDTO atualizarItemPedido(UUID id, ItemPedidoRequestDTO itemPedidoRequestDTO) {
        ItemPedido itemPedido = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ItemPedido não encontrado com o ID: " + id));

        Produto produto = produtoRepository.findById(itemPedidoRequestDTO.idProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + itemPedidoRequestDTO.idProduto()));

        Usuario usuario = usuarioRepository.findById(itemPedidoRequestDTO.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + itemPedidoRequestDTO.idUsuario()));  // Recuperando o Usuario

        itemPedido.setQuantidade(itemPedidoRequestDTO.quantidade());
        itemPedido.setProduto(produto);
        itemPedido.setUsuario(usuario);  // Atualizando o usuário

        ItemPedido itemPedidoAtualizado = itemPedidoRepository.save(itemPedido);
        return itemPedidoMapper.toItemPedidoResponseDTO(itemPedidoAtualizado, produtoMapper.toProdutoResponseDTO(produto), usuario.getIdUsuario());  // Passando o idUsuario
    }
    @Transactional
    @Override
    public List<ItemPedidoResponseDTO> listarItemPedidosPorUsuario(UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + usuarioId));

        List<ItemPedido> itensPedido = itemPedidoRepository.findByUsuario(usuario);

        return itensPedido.stream()
                .map(item -> {
                    Produto produto = item.getProduto();
                    return itemPedidoMapper.toItemPedidoResponseDTO(item, produtoMapper.toProdutoResponseDTO(produto), usuario.getIdUsuario());
                })
                .toList();
    }

}
