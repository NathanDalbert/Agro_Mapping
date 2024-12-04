package com.br.Agro_Mapping.service.mapper;

import com.br.Agro_Mapping.dto.request.PedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ItemPedidoResponseDTO;
import com.br.Agro_Mapping.dto.responses.PedidoResponseDTO;
import com.br.Agro_Mapping.dto.responses.ProdutoResponseDTO;
import com.br.Agro_Mapping.model.ItemPedido;
import com.br.Agro_Mapping.model.Pedido;
import com.br.Agro_Mapping.model.Produto;
import com.br.Agro_Mapping.model.Usuario;
import com.br.Agro_Mapping.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PedidoMapper {
    private final UsuarioMapper usuarioMapper;
    private final ItemPedidoMapper itemPedidoMapper;
    private final ProdutoMapper produtoMapper;
    private final ProdutoRepository produtoRepository; // Necessário para buscar produtos

    public Pedido toPedido(PedidoRequestDTO pedidoRequestDTO, Usuario usuario) {
        Pedido pedido = Pedido.newPedido(
                pedidoRequestDTO.dataPedido(),
                pedidoRequestDTO.itens().stream()
                        .map(itemRequest -> {
                            Produto produto = produtoRepository.findById(itemRequest.idProduto())
                                    .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + itemRequest.idProduto()));

                            // Incluindo o Usuario ao criar o ItemPedido
                            ItemPedido itemPedido = itemPedidoMapper.toItemPedido(itemRequest, produto, usuario);
                            return itemPedido;
                        })
                        .collect(Collectors.toList())
        );

        pedido.setUsuario(usuario);
        return pedido;
    }

    public PedidoResponseDTO toPedidoResponseDTO(Pedido pedido) {
        List<ItemPedidoResponseDTO> itens = pedido.getItempedidos().stream().map(item -> {
            ProdutoResponseDTO produtoResponseDTO = produtoMapper.toProdutoResponseDTO(item.getProduto());

            // Passando o idUsuario para o mapeamento do ItemPedidoResponseDTO
            return itemPedidoMapper.toItemPedidoResponseDTO(item, produtoResponseDTO, item.getUsuario().getIdUsuario());
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
