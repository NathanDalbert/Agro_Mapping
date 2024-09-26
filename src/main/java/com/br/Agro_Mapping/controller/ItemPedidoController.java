package com.br.Agro_Mapping.controller;

import com.br.Agro_Mapping.dto.request.ItemPedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ItemPedidoResponseDTO;
import com.br.Agro_Mapping.service.ItemPedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/itemPedido")
public class ItemPedidoController {

    private final ItemPedidoService itemPedidoService;


    @PostMapping
    public ResponseEntity<ItemPedidoResponseDTO> criarItemPedido(@Valid @RequestBody ItemPedidoRequestDTO itemPedidoRequestDTO) {
        ItemPedidoResponseDTO itemPedidoResponseDTO = itemPedidoService.criarItemPedido(itemPedidoRequestDTO);
        return ResponseEntity.ok(itemPedidoResponseDTO);
    }
    @GetMapping("/")
    public ResponseEntity<List<ItemPedidoResponseDTO>> listarItemPedidos() {
       List <ItemPedidoResponseDTO> itemPedidos = itemPedidoService.listaItemPedidos();
        return ResponseEntity.ok(itemPedidos);
    }

    @PutMapping
    public ResponseEntity<ItemPedidoResponseDTO> atualizarItemPedido(@PathVariable UUID id,
                                                                @Valid @RequestBody ItemPedidoRequestDTO itemPedidoRequestDTO) {
          ItemPedidoResponseDTO itemPedidoAtualizado = itemPedidoService.atualizarItemPedido(id, itemPedidoRequestDTO);
          return ResponseEntity.ok(itemPedidoAtualizado);
     }

     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItemPedido(@PathVariable UUID id) {
         itemPedidoService.deletarItemPedido(id);
         return ResponseEntity.noContent().build();
     }
}
