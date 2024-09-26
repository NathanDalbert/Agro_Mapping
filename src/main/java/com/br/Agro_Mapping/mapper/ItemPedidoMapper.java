package com.br.Agro_Mapping.mapper;

import com.br.Agro_Mapping.dto.request.ItemPedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ItemPedidoResponseDTO;
import com.br.Agro_Mapping.model.ItemPedido;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoMapper {

    public ItemPedido toItemPedido(ItemPedidoRequestDTO itemPedidoRequestDTO) {
        return ItemPedido.newItemPedido(
                itemPedidoRequestDTO.precoUnitario(),
                itemPedidoRequestDTO.quantidade()
        );
    }

    public ItemPedidoResponseDTO toItemPedidoResponseDTO(ItemPedido itemPedido) {
        return new ItemPedidoResponseDTO(
                itemPedido.getId(),
                itemPedido.getPrecoUnitario(),
                itemPedido.getQuantidade()
        );
    }
}
