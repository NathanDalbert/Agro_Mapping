package com.br.Agro_Mapping.service.mapper;

import com.br.Agro_Mapping.dto.request.ItemPedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ItemPedidoResponseDTO;
import com.br.Agro_Mapping.dto.responses.ProdutoResponseDTO;
import com.br.Agro_Mapping.model.ItemPedido;
import com.br.Agro_Mapping.model.Produto;
import com.br.Agro_Mapping.model.Usuario;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ItemPedidoMapper {


    public ItemPedido toItemPedido(ItemPedidoRequestDTO itemPedidoRequestDTO, Produto produto, Usuario usuario) {
        ItemPedido itemPedido = ItemPedido.newItemPedido(
                itemPedidoRequestDTO.quantidade()
        );
        itemPedido.setProduto(produto);
        itemPedido.setUsuario(usuario);
        return itemPedido;
    }


    public ItemPedidoResponseDTO toItemPedidoResponseDTO(ItemPedido itemPedido, ProdutoResponseDTO produtoResponseDTO, UUID usuarioId) {
        return new ItemPedidoResponseDTO(
                itemPedido.getIdItemPedido(),
                produtoResponseDTO,
                itemPedido.getQuantidade(),
                itemPedido.getValorTotalItem(),
                usuarioId
        );
    }
}
