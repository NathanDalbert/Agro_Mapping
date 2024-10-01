package com.br.Agro_Mapping.service.mapper;

import com.br.Agro_Mapping.dto.request.PedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ItemPedidoResponseDTO;
import com.br.Agro_Mapping.dto.responses.PedidoResponseDTO;
import com.br.Agro_Mapping.dto.responses.ProdutoResponseDTO;
import com.br.Agro_Mapping.model.Pedido;
import com.br.Agro_Mapping.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PedidoMapper {
    private final UsuarioMapper usuarioMapper;
    private final ItemPedidoMapper itemPedidoMapper;
    private final ProdutoMapper produtoMapper;

    public Pedido toPedido(PedidoRequestDTO pedidoRequestDTO, Usuario usuario) {
        Pedido pedido = Pedido.newPedido(
                pedidoRequestDTO.dataPedido(),
                pedidoRequestDTO.valorTotal());

        pedido.setUsuario(usuario);
        return pedido;
    }

    public PedidoResponseDTO toPedidoResponseDTO(Pedido pedido) {
        List<ItemPedidoResponseDTO> itens = pedido.getItempedidos().stream().map(item -> {
            ProdutoResponseDTO produtoResponseDTO = produtoMapper.toProdutoResponseDTO(item.getProduto());
            return itemPedidoMapper.toItemPedidoResponseDTO(item, produtoResponseDTO);
        }).toList();

        return new PedidoResponseDTO(
                pedido.getIdPedido(),
                pedido.getDataPedido(),
                pedido.getValorTotal(),
                usuarioMapper.toUsuarioResponseDTO(pedido.getUsuario()),
                itens
        );
    }
}
