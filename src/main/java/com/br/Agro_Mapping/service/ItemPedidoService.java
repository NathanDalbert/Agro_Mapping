package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.ItemPedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ItemPedidoResponseDTO;
import com.br.Agro_Mapping.model.ItemPedido;
import com.br.Agro_Mapping.model.Produto;
import com.br.Agro_Mapping.repository.ItemPedidoRepository;
import com.br.Agro_Mapping.repository.ProdutoRepository;
import com.br.Agro_Mapping.service.mapper.ItemPedidoMapper;
import com.br.Agro_Mapping.service.mapper.ProdutoMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemPedidoService implements ItemPedidoServiceInterface {

    private final ItemPedidoRepository itemPedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoMapper itemPedidoMapper;
    private final ProdutoMapper produtoMapper;

    @Transactional
    @Override
    public ItemPedidoResponseDTO criarItemPedido(ItemPedidoRequestDTO itemPedidoRequestDTO) {

        Produto produto = produtoRepository.findById(itemPedidoRequestDTO.idProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + itemPedidoRequestDTO.idProduto()));


        ItemPedido itemPedido = itemPedidoMapper.toItemPedido(itemPedidoRequestDTO, produto);
        ItemPedido itemPedidoSalvo = itemPedidoRepository.save(itemPedido);

        return itemPedidoMapper.toItemPedidoResponseDTO(itemPedidoSalvo, produtoMapper.toProdutoResponseDTO(produto));
    }

    @Transactional
    @Override
    public List<ItemPedidoResponseDTO> listaItemPedidos() {
        List<ItemPedido> itemPedidos = itemPedidoRepository.findAll();
        return itemPedidos.stream()
                .map(item -> {
                    Produto produto = item.getProduto();
                    return itemPedidoMapper.toItemPedidoResponseDTO(item, produtoMapper.toProdutoResponseDTO(produto));
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


        itemPedido.setPrecoUnitario(itemPedidoRequestDTO.precoUnitario());
        itemPedido.setQuantidade(itemPedidoRequestDTO.quantidade());
        itemPedido.setProduto(produto);


        ItemPedido itemPedidoAtualizado = itemPedidoRepository.save(itemPedido);
        return itemPedidoMapper.toItemPedidoResponseDTO(itemPedidoAtualizado, produtoMapper.toProdutoResponseDTO(produto));
    }
}
