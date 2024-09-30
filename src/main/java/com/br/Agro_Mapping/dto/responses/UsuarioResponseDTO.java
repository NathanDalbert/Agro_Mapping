package com.br.Agro_Mapping.dto.responses;

import java.util.List;
import java.util.UUID;

public record UsuarioResponseDTO(UUID id, String nome, String email, String dataDeNasciment,
                                List<ContatoResponseDTO> contatos ,List<ProdutoResponseDTO> produtos)  {
}
