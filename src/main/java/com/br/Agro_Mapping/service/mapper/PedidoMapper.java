package com.br.Agro_Mapping.service.mapper;

import com.br.Agro_Mapping.dto.request.PedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.PedidoResponseDTO;
import com.br.Agro_Mapping.model.Pedido;
import com.br.Agro_Mapping.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public PedidoMapper(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }
    public Pedido toPedido(PedidoRequestDTO pedidoRequestDTO, Usuario usuario) {
        Pedido pedido = Pedido.newPedido(
                pedidoRequestDTO.dataPedido(),
                pedidoRequestDTO.valorTotal());

        pedido.setUsuario(usuario);
        return pedido;

    }

    public PedidoResponseDTO toPedidoResponseDTO(Pedido pedido) {
        return new PedidoResponseDTO(
                pedido.getIdPedido(),
                pedido.getDataPedido(),
                pedido.getValorTotal(),
                usuarioMapper.toUsuarioResponseDTO(pedido.getUsuario())
        );
    }
}
