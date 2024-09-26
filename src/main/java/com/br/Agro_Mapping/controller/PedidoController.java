package com.br.Agro_Mapping.controller;

import com.br.Agro_Mapping.controller.swagger.PedidoSwagger;
import com.br.Agro_Mapping.dto.request.PedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.PedidoResponseDTO;

import com.br.Agro_Mapping.service.PedidoServiceInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedido")
public class PedidoController implements PedidoSwagger {

    private final PedidoServiceInterface pedidoServiceInterface;

    @Override
    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(@Valid @RequestBody PedidoRequestDTO pedidoRequestDTO) {
        PedidoResponseDTO pedidoResponseDTO = pedidoServiceInterface.criarPedido(pedidoRequestDTO);
        return ResponseEntity.ok(pedidoResponseDTO);
    }

    @Override
    @GetMapping("/")
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos() {
        List<PedidoResponseDTO> pedidos = pedidoServiceInterface.listaPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @Override
    @PutMapping
    public ResponseEntity<PedidoResponseDTO> atualizarPedido(@PathVariable UUID id, @Valid @RequestBody PedidoRequestDTO pedidoRequestDTO) {
        PedidoResponseDTO pedidoAtualizado = pedidoServiceInterface.atualizarPedido(id, pedidoRequestDTO);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable UUID id) {
        pedidoServiceInterface.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
