package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.ItemPedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ItemPedidoResponseDTO;
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

    @Transactional
    @Override
    public ItemPedidoResponseDTO criarItemPedido(ItemPedidoRequestDTO itemPedidoRequestDTO) {
        ItemPedido itemPedido = ItemPedido.newItemPedido(
                itemPedidoRequestDTO.precoUnitario(),
                itemPedidoRequestDTO.quantidade());

        ItemPedido itemPedidoSalvo = itemPedidoRepository.save(itemPedido);

        return new ItemPedidoResponseDTO(
                itemPedidoSalvo.getId(),
                itemPedidoSalvo.getPrecoUnitario(),
                itemPedidoSalvo.getQuantidade()
        );
    }

    @Transactional
    @Override
    public List<ItemPedidoResponseDTO> listaItemPedidos() {
        List<ItemPedido> itemPedidos = itemPedidoRepository.findAll();
        return itemPedidos.stream()
                .map(itemPedido -> new ItemPedidoResponseDTO(
                    itemPedido.getId(),
                    itemPedido.getPrecoUnitario(),
                    itemPedido.getQuantidade()))
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
                .orElseThrow(()->new RuntimeException("ItemPedido não encontrado com o ID: " + id));
        itemPedido.setPrecoUnitario(itemPedidoRequestDTO.precoUnitario());
        itemPedido.setQuantidade(itemPedidoRequestDTO.quantidade());

        ItemPedido itemPedidoAtualizado = itemPedidoRepository.save(itemPedido);

        return new ItemPedidoResponseDTO(
                itemPedidoAtualizado.getId(),
                itemPedidoAtualizado.getPrecoUnitario(),
                itemPedidoAtualizado.getQuantidade()
        );

    }
}
