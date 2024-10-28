package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.UsuarioRequestDTO;
import com.br.Agro_Mapping.dto.responses.ContatoResponseDTO;
import com.br.Agro_Mapping.dto.responses.UsuarioResponseDTO;
import com.br.Agro_Mapping.exceptions.EmailAlreadyExistsException;
import com.br.Agro_Mapping.exceptions.UserUnderageException;
import com.br.Agro_Mapping.exceptions.UsuarioNotFoundException;
import com.br.Agro_Mapping.service.mapper.UsuarioMapper;
import com.br.Agro_Mapping.model.Usuario;
import com.br.Agro_Mapping.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UsuarioServiceInterface   {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public static boolean isMaiorDe18(LocalDate dataNascimento) {
        LocalDate hoje = LocalDate.now();
        Period idade = Period.between(dataNascimento, hoje);
        return idade.getYears() >= 18;
    }

    @Transactional
    @Override
    public UsuarioResponseDTO criarUsuario(UsuarioRequestDTO usuarioRequestDTO) {

        Optional<Usuario> existingUsuario = usuarioRepository.findByEmail(usuarioRequestDTO.email());
        if (existingUsuario.isPresent()) {
            throw new EmailAlreadyExistsException(usuarioRequestDTO.email());
        }

        if (isMaiorDe18(usuarioRequestDTO.dataNascimento())) {
            Usuario usuario = usuarioMapper.toUsuario(usuarioRequestDTO);
            Usuario usuarioSalvo = usuarioRepository.save(usuario);
            return usuarioMapper.toUsuarioResponseDTO(usuarioSalvo);
        }

        throw new UserUnderageException(usuarioRequestDTO.dataNascimento());
    }

    @Transactional
    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::toUsuarioResponseDTO)
                .toList();
    }

    @Transactional
    @Override
    public Optional<UsuarioResponseDTO> buscarUsuarioPorId(UUID id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        return usuarioOptional.map(usuarioMapper::toUsuarioResponseDTO);
    }

    @Transactional
    @Override
    public void deletarUsuario(UUID id) {
        usuarioRepository.deleteById(id);
    }

    @Transactional
    @Override
    public UsuarioResponseDTO atualizarUsuario(UUID id, UsuarioRequestDTO usuarioRequestDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));

        // Verifica se o e-mail já está em uso por outro usuário
        Optional<Usuario> usuarioByEmail = usuarioRepository.findByEmail(usuarioRequestDTO.email());
        if (usuarioByEmail.isPresent() && !usuarioByEmail.get().getIdUsuario().equals(id)) {
            throw new EmailAlreadyExistsException(usuarioRequestDTO.email());
        }

        usuario.setNome(usuarioRequestDTO.nome());
        usuario.setEmail(usuarioRequestDTO.email());
        usuario.setSenha(usuarioRequestDTO.senha());
        usuario.setDataDeNascimento(usuarioRequestDTO.dataNascimento());

        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return usuarioMapper.toUsuarioResponseDTO(usuarioAtualizado);
    }

    @Transactional
    @Override
    public List<UsuarioResponseDTO> findByName(String nome) {
        List<Usuario> usuarios = usuarioRepository.findByNome(nome);
        return usuarios.stream()
                .map(usuarioMapper::toUsuarioResponseDTO)
                .toList();
    }

    @Transactional
    public List<ContatoResponseDTO> buscarContatosPorUsuarioId(UUID id) {
        Usuario usuario = usuarioRepository.findByIdFetchContatos(id);
        return usuarioMapper.toUsuarioResponseDTO(usuario).contatos();
    }
}
