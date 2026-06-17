package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.ReservaRequestDTO;
import com.br.Agro_Mapping.dto.responses.ReservaResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ReservaServiceInterface {
    ReservaResponseDTO criarReserva(ReservaRequestDTO dto);
    List<ReservaResponseDTO> listarPorUsuario(UUID idUsuario);
    List<ReservaResponseDTO> listarPorFeira(UUID idFeira);
    List<ReservaResponseDTO> listarPorVendedor();
    ReservaResponseDTO atualizarStatus(UUID idReserva, String status);
}
