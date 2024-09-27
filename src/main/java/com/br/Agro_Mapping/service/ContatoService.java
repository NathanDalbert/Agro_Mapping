package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.ContatoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ContatoResponseDTO;
import com.br.Agro_Mapping.model.Contato;
import com.br.Agro_Mapping.repository.ContatoRepository;
import com.br.Agro_Mapping.service.mapper.ContatoServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ContatoService implements ContatoServiceInterface{

    private final ContatoRepository contatoRepository;
    private final ContatoServiceMapper contatoServiceMapper;


    @Override
    public ContatoResponseDTO criarContato(ContatoRequestDTO contatoRequestDTO) {
        Contato contato = contatoServiceMapper.toContato(contatoRequestDTO);
        Contato contatoSalvo = contatoRepository.save(contato);
        return contatoServiceMapper.toContatoResponseDTO(contatoSalvo);
    }

    @Override
    public List<ContatoResponseDTO> listarContato() {
        List<Contato> contatos = contatoRepository.findAll();
        return contatos.stream()
                .map(contatoServiceMapper::toContatoResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deletarUsuario(UUID id) {
        contatoRepository.deleteById(id);
    }

    @Override
    public ContatoResponseDTO atualizarContato(UUID id, ContatoRequestDTO contatoRequestDTO) {
        Contato contato = contatoRepository.findById(id).orElseThrow(()->new RuntimeException("Contato não encontrado com o ID " + id));

        contato.setTelefone(contatoRequestDTO.telefone());

        Contato contatoAtualizado = contatoRepository.save(contato);
        return contatoServiceMapper.toContatoResponseDTO(contatoAtualizado);
    }
}
