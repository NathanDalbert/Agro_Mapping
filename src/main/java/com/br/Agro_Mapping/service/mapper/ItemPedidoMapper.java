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

        public ItemPedido toItemPedido(ItemPedidoRequestDTO itemPedidoRequestDTO, Produto produto) {
            ItemPedido itemPedido = ItemPedido.newItemPedido(
                    itemPedidoRequestDTO.quantidade()
            );
            itemPedido.setProduto(produto);
            return itemPedido;
        }

        public ItemPedidoResponseDTO toItemPedidoResponseDTO(ItemPedido itemPedido, ProdutoResponseDTO produtoResponseDTO) {
            return new ItemPedidoResponseDTO(
                    itemPedido.getIdItemPedido(),
                    produtoResponseDTO,
                    itemPedido.getQuantidade(),
                    itemPedido.getValorTotalItem()
            );
        }
    }
