package com.br.Agro_Mapping.service.mapper;

import com.br.Agro_Mapping.dto.request.EstoqueRequestDTO;
import com.br.Agro_Mapping.dto.responses.EstoqueResponseDTO;
import com.br.Agro_Mapping.model.Estoque;
import com.br.Agro_Mapping.model.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class EstoqueMapper {

    // Método para converter EstoqueRequestDTO em Estoque
    public Estoque toEstoque(EstoqueRequestDTO dto, Produto produto) {
        Estoque estoque = new Estoque();
        estoque.setQuantidade(dto.quantidade());


        estoque.setProdutos(Collections.singletonList(produto));

        return estoque;
    }


    public EstoqueResponseDTO toEstoqueResponseDTO(Estoque estoque) {
        return new EstoqueResponseDTO(
                estoque.getIdEstoque(),
                estoque.getQuantidade(),
                estoque.getProdutos().get(0).getIdProduto()
        );
    }
}
