package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.ContatoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ContatoResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ContatoServiceInterface {
    ContatoResponseDTO criarContato(ContatoRequestDTO contatoRequestDTO, UUID usuarioId); // Adicionando usuarioId
    List<ContatoResponseDTO> listarContato();
    void deletarContato(UUID id);
    ContatoResponseDTO atualizarContato(UUID id, ContatoRequestDTO contatoRequestDTO);
}
