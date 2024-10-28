package com.br.Agro_Mapping.service.mapper;

import com.br.Agro_Mapping.dto.request.EstoqueRequestDTO;
import com.br.Agro_Mapping.dto.responses.EstoqueResponseDTO;
import com.br.Agro_Mapping.model.Estoque;
import com.br.Agro_Mapping.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class EstoqueMapper {

    public Estoque toEstoque(EstoqueRequestDTO estoqueRequestDTO, Produto produto) {
        Estoque estoque = new Estoque();
        estoque.setProduto(produto);
        estoque.setQuantidadeDisponivel(estoqueRequestDTO.quantidadeDisponivel());
        return estoque;
    }

    public EstoqueResponseDTO toEstoqueResponseDTO(Estoque estoque) {
        return new EstoqueResponseDTO(
                estoque.getIdEstoque(),
                estoque.getProduto().getIdProduto(),
                estoque.getQuantidadeDisponivel()
        );
    }
}
