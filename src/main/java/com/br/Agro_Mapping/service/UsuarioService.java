package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.UsuarioRequestDTO;
import com.br.Agro_Mapping.dto.responses.UsuarioResponseDTO;
import com.br.Agro_Mapping.model.Usuario;
import com.br.Agro_Mapping.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UsuarioServiceInterface {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = Usuario.newUsuario(
                usuarioRequestDTO.nome(),
                usuarioRequestDTO.email(),
                usuarioRequestDTO.senha(),
                usuarioRequestDTO.dataNascimento()
        );

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(usuarioSalvo.getId(), usuarioSalvo.getNome(), usuarioSalvo.getEmail(), usuario.getDataDeNascimento());
    }

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuario -> new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getDataDeNascimento()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UsuarioResponseDTO> buscarUsuarioPorId(UUID id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        return usuarioOptional.map(usuario -> new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getDataDeNascimento()));
    }

    @Override
    public void deletarUsuario(UUID id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioResponseDTO atualizarUsuario(UUID id, UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));

        usuario.setNome(usuarioRequestDTO.nome());
        usuario.setEmail(usuarioRequestDTO.email());
        usuario.setSenha(usuarioRequestDTO.senha());
        usuario.setDataDeNascimento(usuarioRequestDTO.dataNascimento());

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                usuarioAtualizado.getId(),
                usuarioAtualizado.getNome(),
                usuarioAtualizado.getEmail(),
                usuario.getDataDeNascimento());
    }

    @Override
    public List<UsuarioResponseDTO> findByName(String nome) {
        List<Usuario> usuarios = usuarioRepository.findByNome(nome);
        return usuarios.stream()
                .map(usuario -> new UsuarioResponseDTO(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getDataDeNascimento())).
                 toList();
    }

}
