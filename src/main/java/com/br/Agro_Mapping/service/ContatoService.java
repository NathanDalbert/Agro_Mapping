package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.ContatoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ContatoResponseDTO;
import com.br.Agro_Mapping.exceptions.ContatoNotFoundException;
import com.br.Agro_Mapping.exceptions.UsuarioNotFoundException;
import com.br.Agro_Mapping.model.Contato;
import com.br.Agro_Mapping.model.Usuario;
import com.br.Agro_Mapping.repository.ContatoRepository;
import com.br.Agro_Mapping.repository.UsuarioRepository;
import com.br.Agro_Mapping.service.mapper.ContatoServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContatoService implements ContatoServiceInterface {

    private final ContatoRepository contatoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ContatoServiceMapper contatoServiceMapper;

    public ContatoResponseDTO criarContato(ContatoRequestDTO contatoRequestDTO, UUID usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNotFoundException(usuarioId));

        Contato contato = contatoServiceMapper.toContato(contatoRequestDTO,usuario);

        Contato contatoSalvo = contatoRepository.save(contato);

        return contatoServiceMapper.toContatoResponseDTO(contatoSalvo);
    }

    @Override
    public List<ContatoResponseDTO> listarContato() {
        List<Contato> contatos = contatoRepository.findAll();
        return contatos.stream()
                .map(contatoServiceMapper::toContatoResponseDTO)
                .toList();
    }

    @Override
    public void deletarContato(UUID id) {
        contatoRepository.deleteById(id);
    }

    @Override
    public ContatoResponseDTO atualizarContato(UUID id, ContatoRequestDTO contatoRequestDTO) {
        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new ContatoNotFoundException(id));

        contato.setTelefone(contatoRequestDTO.telefone());

        Contato contatoAtualizado = contatoRepository.save(contato);
        return contatoServiceMapper.toContatoResponseDTO(contatoAtualizado);
    }
}
