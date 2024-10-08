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

    private final PedidoServiceInterface pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> criarPedido(@Valid @RequestBody PedidoRequestDTO pedidoRequestDTO) {
        PedidoResponseDTO pedidoResponseDTO = pedidoService.criarPedido(pedidoRequestDTO);
        return ResponseEntity.ok(pedidoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos() {
        List<PedidoResponseDTO> pedidos = pedidoService.listaPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> atualizarPedido(@PathVariable UUID id, @Valid @RequestBody PedidoRequestDTO pedidoRequestDTO) {
        PedidoResponseDTO pedidoAtualizado = pedidoService.atualizarPedido(id, pedidoRequestDTO);
        return ResponseEntity.ok(pedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPedido(@PathVariable UUID id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidosPorUsuario(@PathVariable UUID idUsuario) {
        List<PedidoResponseDTO> pedidos = pedidoService.listaPedidosPorUsuario(idUsuario);
        return ResponseEntity.ok(pedidos);
    }
}
