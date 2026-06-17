package com.br.Agro_Mapping.controller;

import com.br.Agro_Mapping.dto.request.ReservaRequestDTO;
import com.br.Agro_Mapping.dto.responses.ReservaResponseDTO;
import com.br.Agro_Mapping.service.ReservaServiceInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reserva")
public class ReservaController {

    private final ReservaServiceInterface reservaService;

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> criar(@Valid @RequestBody ReservaRequestDTO dto) {
        return ResponseEntity.ok(reservaService.criarReserva(dto));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ReservaResponseDTO>> listarPorUsuario(@PathVariable UUID idUsuario) {
        return ResponseEntity.ok(reservaService.listarPorUsuario(idUsuario));
    }

    @GetMapping("/feira/{idFeira}")
    public ResponseEntity<List<ReservaResponseDTO>> listarPorFeira(@PathVariable UUID idFeira) {
        return ResponseEntity.ok(reservaService.listarPorFeira(idFeira));
    }

    @GetMapping("/meus-produtos")
    public ResponseEntity<List<ReservaResponseDTO>> listarPorVendedor() {
        return ResponseEntity.ok(reservaService.listarPorVendedor());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ReservaResponseDTO> atualizarStatus(
            @PathVariable UUID id,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(reservaService.atualizarStatus(id, body.get("status")));
    }
}
