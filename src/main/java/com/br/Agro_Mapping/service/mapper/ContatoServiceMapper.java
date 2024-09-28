package com.br.Agro_Mapping.service.mapper;

import com.br.Agro_Mapping.dto.request.ContatoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ContatoResponseDTO;
import com.br.Agro_Mapping.model.Contato;
import com.br.Agro_Mapping.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ContatoServiceMapper {

    public Contato toContato(ContatoRequestDTO contatoRequestDTO, Usuario usuario) {
        Contato contato = Contato.newContato(
                contatoRequestDTO.telefone()
        );
        contato.setUsuario(usuario);
        return contato;
    }

    public ContatoResponseDTO toContatoResponseDTO(Contato contato) {
        return new ContatoResponseDTO(
                contato.getIdTelefone(),
                contato.getTelefone()
        );
    }
}
