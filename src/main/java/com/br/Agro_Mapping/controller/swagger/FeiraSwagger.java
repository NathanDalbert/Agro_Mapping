package com.br.Agro_Mapping.controller.swagger;

import com.br.Agro_Mapping.dto.request.FeiraRequestDTO;
import com.br.Agro_Mapping.dto.responses.FeiraResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface FeiraSwagger {

    @Operation(summary = "Cria uma nova feira")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Feira criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    ResponseEntity<FeiraResponseDTO> criarFeira(@RequestBody FeiraRequestDTO feiraRequestDTO);

    @Operation(summary = "Lista todas as feiras")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de feiras retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/")
    ResponseEntity<List<FeiraResponseDTO>> listarFeiras();

    @Operation(summary = "Busca uma feira por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Feira encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Feira não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    ResponseEntity<FeiraResponseDTO> buscarFeiraPorId(@PathVariable UUID id);

    @Operation(summary = "Atualiza uma feira existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Feira atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Feira não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    ResponseEntity<FeiraResponseDTO> atualizarFeira(@PathVariable UUID id, @RequestBody FeiraRequestDTO feiraRequestDTO);

    @Operation(summary = "Deleta uma feira por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Feira deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Feira não encontrada"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletarFeira(@PathVariable UUID id);
}
