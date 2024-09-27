package com.br.Agro_Mapping.service.mapper;

import com.br.Agro_Mapping.dto.request.ContatoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ContatoResponseDTO;
import com.br.Agro_Mapping.model.Contato;

public class ContatoServiceMapper {

    public Contato toContato(ContatoRequestDTO contatoRequestDTO){
        return Contato.newContato(
                contatoRequestDTO.telefone()
        );
    }

    public ContatoResponseDTO toContatoResponseDTO(Contato contato){
        return new ContatoResponseDTO(
                contato.getId(),
                contato.getTelefone()
        );
    }
}
