package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.PedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.PedidoResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PedidoServiceInterface {
    PedidoResponseDTO criarPedido(PedidoRequestDTO pedidoRequestDTO);
    List<PedidoResponseDTO> listaPedidos();
    void deletarPedido(UUID id);
    PedidoResponseDTO atualizarPedido(UUID id, PedidoRequestDTO pedidoRequestDTO);

}
