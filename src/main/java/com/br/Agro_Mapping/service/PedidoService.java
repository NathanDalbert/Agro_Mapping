package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.PedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.PedidoResponseDTO;
import com.br.Agro_Mapping.model.ItemPedido;
import com.br.Agro_Mapping.model.Usuario;
import com.br.Agro_Mapping.repository.ItemPedidoRepository;
import com.br.Agro_Mapping.repository.UsuarioRepository;
import com.br.Agro_Mapping.service.mapper.PedidoMapper;
import com.br.Agro_Mapping.model.Pedido;
import com.br.Agro_Mapping.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PedidoService implements PedidoServiceInterface {

    private  final ItemPedidoRepository itemPedidoRepository;

    private final PedidoRepository pedidoRepository;

    private final UsuarioRepository usuarioRepository;

    private final PedidoMapper pedidoMapper;


    @Transactional

    public PedidoResponseDTO criarPedido(PedidoRequestDTO pedidoRequestDTO) {
        Usuario usuario = usuarioRepository.findById(pedidoRequestDTO.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + pedidoRequestDTO.idUsuario()));
        Pedido pedido = pedidoMapper.toPedido(pedidoRequestDTO,usuario);
        pedido.setUsuario(usuario);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        for(ItemPedido item: pedido.getItempedidos()){
            item.setPedido(pedidoSalvo);
            itemPedidoRepository.save(item);
        }
        return pedidoMapper.toPedidoResponseDTO(pedidoSalvo);



    }

    @Transactional(readOnly = true)

    public List<PedidoResponseDTO> listaPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
                .map(pedidoMapper::toPedidoResponseDTO)
                .toList();
    }

    @Transactional

    public void deletarPedido(UUID id) {
        pedidoRepository.deleteById(id);
    }

    @Transactional

    public PedidoResponseDTO atualizarPedido(UUID id, PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + id));
        pedido.setDataPedido(pedidoRequestDTO.dataPedido());
        pedido.setValorTotal(pedidoRequestDTO.valorTotal());

        Pedido pedidoAtualizado = pedidoRepository.save(pedido);

        return pedidoMapper.toPedidoResponseDTO(pedidoAtualizado);
    }
    @Transactional
    public List<PedidoResponseDTO> listaPedidosPorUsuario(UUID idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return pedidoRepository.findByUsuario(usuario).stream()
                .map(pedidoMapper::toPedidoResponseDTO)
                .toList();
    }
}
