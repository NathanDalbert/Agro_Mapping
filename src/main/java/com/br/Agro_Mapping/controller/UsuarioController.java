package com.br.Agro_Mapping.controller;

import com.br.Agro_Mapping.controller.swagger.UsuarioSwagger;
import com.br.Agro_Mapping.dto.request.UsuarioRequestDTO;
import com.br.Agro_Mapping.dto.responses.UsuarioResponseDTO;
import com.br.Agro_Mapping.service.UsuarioServiceInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")

public class UsuarioController implements UsuarioSwagger {

    private final UsuarioServiceInterface usuarioServiceInterface;


    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO usuarioResponseDTO = usuarioServiceInterface.criarUsuario(usuarioRequestDTO);
        return ResponseEntity.ok(usuarioResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        List<UsuarioResponseDTO> usuarios = usuarioServiceInterface.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable UUID id) {
        Optional<UsuarioResponseDTO> usuario = usuarioServiceInterface.buscarUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable UUID id,
                                                               @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        UsuarioResponseDTO usuarioAtualizado = usuarioServiceInterface.atualizarUsuario(id, usuarioRequestDTO);
        return ResponseEntity.ok(usuarioAtualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable UUID id) {
        usuarioServiceInterface.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarUsuarioPorNome/nome/{nome}")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarUsuarioPorNome(@PathVariable("nome") String nome) {
        List<UsuarioResponseDTO> usuarios = usuarioServiceInterface.findByName(nome);
        return ResponseEntity.ok(usuarios);
    }
}
