package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.PedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.PedidoResponseDTO;
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

    private final PedidoRepository pedidoRepository;

    @Transactional
    @Override
    public PedidoResponseDTO criarPedido(PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = Pedido.newPedido(
                pedidoRequestDTO.dataPedido(),
                pedidoRequestDTO.valorTotal());

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return new PedidoResponseDTO(
                pedidoSalvo.getIdPedido(),
                pedidoSalvo.getDataPedido(),
                pedidoSalvo.getValorTotal()
        );
    }

    @Transactional(readOnly = true)
    @Override
    public List<PedidoResponseDTO> ListaPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
                .map(pedido -> new PedidoResponseDTO(
                        pedido.getIdPedido(),
                        pedido.getDataPedido(),
                        pedido.getValorTotal()))
                .toList();
    }

    @Transactional
    @Override
    public void deletarPedido(UUID id) {
        pedidoRepository.deleteById(id);
    }

    @Transactional
    @Override
    public PedidoResponseDTO atualizarPedido(UUID id, PedidoRequestDTO pedidoRequestDTO) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + id));
        pedido.setDataPedido(pedidoRequestDTO.dataPedido());
        pedido.setValorTotal(pedidoRequestDTO.valorTotal());

        Pedido pedidoAtualizado = pedidoRepository.save(pedido);

        return new PedidoResponseDTO(
                pedidoAtualizado.getIdPedido(),
                pedidoAtualizado.getDataPedido(),
                pedidoAtualizado.getValorTotal()
        );
    }
}
