package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.ItemPedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ItemPedidoResponseDTO;
import com.br.Agro_Mapping.dto.responses.PedidoResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ItemPedidoServiceInterface {
    ItemPedidoResponseDTO criarItemPedido(ItemPedidoRequestDTO itemPedidoRequestDTO);
    List<ItemPedidoResponseDTO> listaItemPedidos();
    void deletarItemPedido(UUID id);
    ItemPedidoResponseDTO atualizarItemPedido(UUID id, ItemPedidoRequestDTO itemPedidoRequestDTO);

}
