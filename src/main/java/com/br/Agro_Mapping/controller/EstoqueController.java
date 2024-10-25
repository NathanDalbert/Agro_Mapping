package com.br.Agro_Mapping.controller;

import com.br.Agro_Mapping.dto.request.EstoqueRequestDTO;
import com.br.Agro_Mapping.dto.responses.EstoqueResponseDTO;
import com.br.Agro_Mapping.service.EstoqueServiceInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueServiceInterface estoqueServiceInterface;

    @PostMapping
    public ResponseEntity<EstoqueResponseDTO> criarEstoque(@Valid @RequestBody EstoqueRequestDTO estoqueRequestDTO,
                                                           @RequestParam UUID produtoId) {
        EstoqueResponseDTO estoqueResponseDTO = estoqueServiceInterface.criarEstoque(estoqueRequestDTO, produtoId);
        return ResponseEntity.ok(estoqueResponseDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<EstoqueResponseDTO>> listarEstoques() {
        List<EstoqueResponseDTO> estoques = estoqueServiceInterface.listarEstoques();
        return ResponseEntity.ok(estoques);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstoqueResponseDTO> atualizarEstoque(@PathVariable UUID id,
                                                               @Valid @RequestBody EstoqueRequestDTO estoqueRequestDTO) {
        EstoqueResponseDTO estoqueAtualizado = estoqueServiceInterface.atualizarEstoque(id, estoqueRequestDTO);
        return ResponseEntity.ok(estoqueAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEstoque(@PathVariable UUID id) {
        estoqueServiceInterface.deletarEstoque(id);
        return ResponseEntity.noContent().build();
    }
}
