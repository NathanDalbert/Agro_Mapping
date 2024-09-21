package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.model.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioServiceInterface {
    Usuario salvarUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();
    Optional<Usuario> buscarUsuarioPorId(UUID id);
    void deletarUsuario(UUID id);
    Usuario atualizarUsuario(Usuario usuario);
}
