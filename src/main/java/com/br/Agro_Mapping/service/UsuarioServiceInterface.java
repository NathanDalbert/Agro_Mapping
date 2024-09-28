package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.UsuarioRequestDTO;
import com.br.Agro_Mapping.dto.responses.ContatoResponseDTO;
import com.br.Agro_Mapping.dto.responses.UsuarioResponseDTO;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioServiceInterface {
    UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioRequestDTO);
    List<UsuarioResponseDTO> listarUsuarios();
    Optional<UsuarioResponseDTO> buscarUsuarioPorId(UUID id);
    void deletarUsuario(UUID id);
    UsuarioResponseDTO atualizarUsuario(UUID id, UsuarioRequestDTO usuarioRequestDTO);
    List<UsuarioResponseDTO> findByName(String name);
    List<ContatoResponseDTO> buscarContatosPorUsuarioId(UUID id);


}
