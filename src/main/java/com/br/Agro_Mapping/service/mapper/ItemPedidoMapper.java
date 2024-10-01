package com.br.Agro_Mapping.service.mapper;

import com.br.Agro_Mapping.dto.request.ItemPedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ItemPedidoResponseDTO;
import com.br.Agro_Mapping.dto.responses.ProdutoResponseDTO;
import com.br.Agro_Mapping.model.ItemPedido;
import com.br.Agro_Mapping.model.Pedido;
import com.br.Agro_Mapping.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ItemPedidoMapper {

    public ItemPedido toItemPedido(ItemPedidoRequestDTO itemPedidoRequestDTO, Pedido pedido, Produto produto) {
        ItemPedido itemPedido = ItemPedido.newItemPedido(
                itemPedidoRequestDTO.precoUnitario(),
                itemPedidoRequestDTO.quantidade()
        );
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        return itemPedido;
    }

    public ItemPedidoResponseDTO toItemPedidoResponseDTO(ItemPedido itemPedido, ProdutoResponseDTO produtoResponseDTO) {
        return new ItemPedidoResponseDTO(
                itemPedido.getIdItemPedido(),
                itemPedido.getPrecoUnitario(),
                produtoResponseDTO,
                itemPedido.getQuantidade(),
                itemPedido.getValorTotalItem()
        );
    }
}
