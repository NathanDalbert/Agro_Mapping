package com.br.Agro_Mapping.controller;


import com.br.Agro_Mapping.controller.swagger.FeiraSwagger;
import com.br.Agro_Mapping.dto.request.FeiraRequestDTO;
import com.br.Agro_Mapping.dto.responses.FeiraResponseDTO;
import com.br.Agro_Mapping.service.FeiraServiceInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feiras")
public class FeiraController implements FeiraSwagger {

    private final FeiraServiceInterface feiraServiceInterface;

    @PostMapping
    public ResponseEntity<FeiraResponseDTO> criarFeira(@Valid @RequestBody FeiraRequestDTO feiraRequestDTO) {
        FeiraResponseDTO feiraResponseDTO = feiraServiceInterface.criarFeira(feiraRequestDTO);
        return ResponseEntity.ok(feiraResponseDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<FeiraResponseDTO>> listarFeiras() {
        List<FeiraResponseDTO> feiras = feiraServiceInterface.listarFeiras();
        return ResponseEntity.ok(feiras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeiraResponseDTO> buscarFeiraPorId(@PathVariable UUID id) {
        FeiraResponseDTO feiraResponseDTO = feiraServiceInterface.buscarFeiraPorId(id);
        return ResponseEntity.ok(feiraResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeiraResponseDTO> atualizarFeira(@PathVariable UUID id,
                                                           @Valid @RequestBody FeiraRequestDTO feiraRequestDTO) {
        FeiraResponseDTO feiraAtualizada = feiraServiceInterface.atualizarFeira(id, feiraRequestDTO);
        return ResponseEntity.ok(feiraAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFeira(@PathVariable UUID id) {
        feiraServiceInterface.deletarFeira(id);
        return ResponseEntity.noContent().build();
    }
}
