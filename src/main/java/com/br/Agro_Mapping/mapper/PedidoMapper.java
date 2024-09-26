package com.br.Agro_Mapping.mapper;

import com.br.Agro_Mapping.dto.request.PedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.PedidoResponseDTO;
import com.br.Agro_Mapping.model.Pedido;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {
    public Pedido toPedido(PedidoRequestDTO pedidoRequestDTO) {
        return Pedido.newPedido(
                pedidoRequestDTO.dataPedido(),
                pedidoRequestDTO.valorTotal());


    }

    public PedidoResponseDTO toPedidoResponseDTO(Pedido pedido) {
        return new PedidoResponseDTO(
                pedido.getIdPedido(),
                pedido.getDataPedido(),
                pedido.getValorTotal()
        );
    }
}
