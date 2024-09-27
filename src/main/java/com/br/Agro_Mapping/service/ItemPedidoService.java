package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.ItemPedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ItemPedidoResponseDTO;
import com.br.Agro_Mapping.service.mapper.ItemPedidoMapper;
import com.br.Agro_Mapping.model.ItemPedido;
import com.br.Agro_Mapping.repository.ItemPedidoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemPedidoService implements ItemPedidoServiceInterface {

    private final ItemPedidoRepository itemPedidoRepository;
    private final ItemPedidoMapper itemPedidoMapper;

    @Transactional
    @Override
    public ItemPedidoResponseDTO criarItemPedido(ItemPedidoRequestDTO itemPedidoRequestDTO) {
        ItemPedido itemPedido = itemPedidoMapper.toItemPedido(itemPedidoRequestDTO);
        ItemPedido itemPedidoSalvo = itemPedidoRepository.save(itemPedido);
        return itemPedidoMapper.toItemPedidoResponseDTO(itemPedidoSalvo);
    }

    @Transactional
    @Override
    public List<ItemPedidoResponseDTO> listaItemPedidos() {
        List<ItemPedido> itemPedidos = itemPedidoRepository.findAll();
        return itemPedidos.stream()
                .map(itemPedidoMapper::toItemPedidoResponseDTO)
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

        itemPedido.setPrecoUnitario(itemPedidoRequestDTO.precoUnitario());
        itemPedido.setQuantidade(itemPedidoRequestDTO.quantidade());

        ItemPedido itemPedidoAtualizado = itemPedidoRepository.save(itemPedido);
        return itemPedidoMapper.toItemPedidoResponseDTO(itemPedidoAtualizado);
    }
}
