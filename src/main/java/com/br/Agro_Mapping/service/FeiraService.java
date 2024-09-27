package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.FeiraRequestDTO;
import com.br.Agro_Mapping.dto.responses.FeiraResponseDTO;
import com.br.Agro_Mapping.model.Feira;
import com.br.Agro_Mapping.repository.FeiraRepository;
import com.br.Agro_Mapping.service.mapper.FeiraServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeiraService implements FeiraServiceInterface {

    private final FeiraRepository feiraRepository;
    private final FeiraServiceMapper feiraMapper;

    @Transactional
    @Override
    public FeiraResponseDTO criarFeira(FeiraRequestDTO feiraRequestDTO) {
        Feira feira = feiraMapper.toFeira(feiraRequestDTO);
        Feira feiraSalva = feiraRepository.save(feira);
        return feiraMapper.toFeiraResponseDTO(feiraSalva);
    }

    @Transactional(readOnly = true)
    @Override
    public List<FeiraResponseDTO> listarFeiras() {
        List<Feira> feiras = feiraRepository.findAll();
        return feiras.stream()
                .map(feiraMapper::toFeiraResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public FeiraResponseDTO buscarFeiraPorId(UUID id) {
        Feira feira = feiraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feira não encontrada com o ID: " + id));
        return feiraMapper.toFeiraResponseDTO(feira);
    }

    @Transactional
    @Override
    public FeiraResponseDTO atualizarFeira(UUID id, FeiraRequestDTO feiraRequestDTO) {
        Feira feira = feiraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feira não encontrada com o ID: " + id));
        feira.setNome(feiraRequestDTO.nome());
        feira.setLocalizacao(feiraRequestDTO.localizacao());
        feira.setDataFuncionamento(feiraRequestDTO.dataFuncionamento());

        Feira feiraAtualizada = feiraRepository.save(feira);
        return feiraMapper.toFeiraResponseDTO(feiraAtualizada);
    }

    @Transactional
    @Override
    public void deletarFeira(UUID id) {
        if (feiraRepository.existsById(id)) {
            feiraRepository.deleteById(id);
        } else {
            throw new RuntimeException("Feira não encontrada com o ID: " + id);
        }
    }
}
