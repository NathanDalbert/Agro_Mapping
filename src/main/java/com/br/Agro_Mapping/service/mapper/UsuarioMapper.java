package com.br.Agro_Mapping.service.mapper;

import com.br.Agro_Mapping.dto.request.UsuarioRequestDTO;
import com.br.Agro_Mapping.dto.responses.UsuarioResponseDTO;
import com.br.Agro_Mapping.model.Usuario;
import org.springframework.stereotype.Component;


import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final ContatoServiceMapper contatoServiceMapper;

    public UsuarioMapper(ContatoServiceMapper contatoServiceMapper) {
        this.contatoServiceMapper = contatoServiceMapper;
    }

    public Usuario toUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        return Usuario.newUsuario(
                usuarioRequestDTO.nome(),
                usuarioRequestDTO.email(),
                usuarioRequestDTO.senha(),
                usuarioRequestDTO.dataNascimento()
        );
    }

    public UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario) {
        String formattedDate = usuario.getDataDeNascimento().format(OUTPUT_FORMATTER);

     
        return new UsuarioResponseDTO(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                formattedDate,
                usuario.getContatos()
                        .stream()
                        .map(contato -> contatoServiceMapper.toContatoResponseDTO(contato))
                        .collect(Collectors.toList())
        );
    }

}
