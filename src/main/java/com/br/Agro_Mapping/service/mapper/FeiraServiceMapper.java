package com.br.Agro_Mapping.service.mapper;


import com.br.Agro_Mapping.dto.request.FeiraRequestDTO;
import com.br.Agro_Mapping.dto.responses.FeiraResponseDTO;
import com.br.Agro_Mapping.model.Feira;
import org.springframework.stereotype.Component;

@Component
public class FeiraServiceMapper {

    public Feira toFeira(FeiraRequestDTO dto) {
        return Feira.newFeira(
                dto.nome(),
                dto.localizacao(),
                dto.dataFuncionamento()
        );
    }

    public FeiraResponseDTO toFeiraResponseDTO(Feira feira) {
        return new FeiraResponseDTO(
                feira.getIdFeira(),
                feira.getNome(),
                feira.getLocalizacao(),
                feira.getDataFuncionamento()
        );
    }
}
