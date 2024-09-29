package com.br.Agro_Mapping.controller.swagger;

import com.br.Agro_Mapping.dto.request.ContatoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ContatoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public interface ContatoSwagger {

    @Operation(summary = "Cria um novo contato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    ResponseEntity<ContatoResponseDTO> criarContato(@RequestBody ContatoRequestDTO contatoRequestDTO,
                                                    @RequestParam UUID usuarioId);

    @Operation(summary = "Lista todos os contatos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de contatos retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    ResponseEntity<List<ContatoResponseDTO>> listarContatos();

    @Operation(summary = "Atualiza um contato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Contato não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    ResponseEntity<ContatoResponseDTO> atualizarContato(@PathVariable UUID id,
                                                        @RequestBody ContatoRequestDTO contatoRequestDTO);

    @Operation(summary = "Deleta um contato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Contato deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Contato não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletarContato(@PathVariable UUID id);
}
