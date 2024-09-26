package com.br.Agro_Mapping.controller.swagger;

import com.br.Agro_Mapping.dto.request.ItemPedidoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ItemPedidoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface ItemPedidoSwagger {

    @Operation(summary = "Cria um novo item de pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item de pedido criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    ResponseEntity<ItemPedidoResponseDTO> criarItemPedido(@RequestBody ItemPedidoRequestDTO itemPedidoRequestDTO);

    @Operation(summary = "Lista todos os itens de pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de itens de pedido retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/")
    ResponseEntity<List<ItemPedidoResponseDTO>> listarItemPedidos();

    @Operation(summary = "Atualiza um item de pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item de pedido atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Item de pedido não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    ResponseEntity<ItemPedidoResponseDTO> atualizarItemPedido(@PathVariable UUID id, @RequestBody ItemPedidoRequestDTO itemPedidoRequestDTO);

    @Operation(summary = "Deleta um item de pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item de pedido deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Item de pedido não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletarItemPedido(@PathVariable UUID id);
}
