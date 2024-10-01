package com.br.Agro_Mapping.controller;

import com.br.Agro_Mapping.controller.swagger.ContatoSwagger;
import com.br.Agro_Mapping.dto.request.ContatoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ContatoResponseDTO;
import com.br.Agro_Mapping.service.ContatoServiceInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contato")
public class ContatoController implements ContatoSwagger {

    private final ContatoServiceInterface contatoServiceInterface;

    @PostMapping
    public ResponseEntity<ContatoResponseDTO> criarContato(
            @Valid @RequestBody ContatoRequestDTO contatoRequestDTO,
            @RequestParam UUID usuarioId) {
        ContatoResponseDTO contatoResponseDTO = contatoServiceInterface.criarContato(contatoRequestDTO, usuarioId);
        return ResponseEntity.ok(contatoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ContatoResponseDTO>> listarContatos() {
        List<ContatoResponseDTO> contatos = contatoServiceInterface.listarContato();
        return ResponseEntity.ok(contatos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoResponseDTO> atualizarContato(@PathVariable UUID id, @Valid @RequestBody ContatoRequestDTO contatoRequestDTO) {
        ContatoResponseDTO contatoAtualizado = contatoServiceInterface.atualizarContato(id, contatoRequestDTO);
        return ResponseEntity.ok(contatoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarContato(@PathVariable UUID id) {
        contatoServiceInterface.deletarContato(id);
        return ResponseEntity.noContent().build();
    }
}
