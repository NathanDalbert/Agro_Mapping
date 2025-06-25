package com.br.Agro_Mapping.controller;

import com.br.Agro_Mapping.controller.swagger.ItemPedidoSwagger;
import com.br.Agro_Mapping.dto.request.ItemPedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ItemPedidoResponseDTO;
import com.br.Agro_Mapping.service.ItemPedidoServiceInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/itemPedido")
public class ItemPedidoController implements ItemPedidoSwagger {

    private final ItemPedidoServiceInterface itemPedidoServiceInterface;

    @PostMapping
    public ResponseEntity<ItemPedidoResponseDTO> criarItemPedido(@Valid @RequestBody ItemPedidoRequestDTO itemPedidoRequestDTO) {
        ItemPedidoResponseDTO itemPedidoResponseDTO = itemPedidoServiceInterface.criarItemPedido(itemPedidoRequestDTO);
        return ResponseEntity.ok(itemPedidoResponseDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<ItemPedidoResponseDTO>> listarItemPedidos() {
        List<ItemPedidoResponseDTO> itemPedidos = itemPedidoServiceInterface.listaItemPedidos();
        return ResponseEntity.ok(itemPedidos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedidoResponseDTO> atualizarItemPedido(@PathVariable UUID id,
                                                                     @Valid @RequestBody ItemPedidoRequestDTO itemPedidoRequestDTO) {
        ItemPedidoResponseDTO itemPedidoAtualizado = itemPedidoServiceInterface.atualizarItemPedido(id, itemPedidoRequestDTO);
        return ResponseEntity.ok(itemPedidoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItemPedido(@PathVariable UUID id) {
        itemPedidoServiceInterface.deletarItemPedido(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ItemPedidoResponseDTO>> listarItemPedidosPorUsuario(@PathVariable UUID usuarioId) {
        List<ItemPedidoResponseDTO> itemPedidos = itemPedidoServiceInterface.listarItemPedidosPorUsuario(usuarioId);
        return ResponseEntity.ok(itemPedidos);
    }

}
