package com.br.Agro_Mapping.service.mapper;

import com.br.Agro_Mapping.dto.request.ProdutoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ProdutoResponseDTO;
import com.br.Agro_Mapping.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {



    public Produto toProduto(ProdutoRequestDTO produtoRequestDTO) {
        return Produto.newProduto(
                produtoRequestDTO.nome(),
                produtoRequestDTO.categoria(),
                produtoRequestDTO.quantidadeDisponivel(),
                produtoRequestDTO.preco(),
                produtoRequestDTO.descricao(),
                produtoRequestDTO.imagem()
        );
    }
    public ProdutoResponseDTO toProdutoResponseDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getIdProduto(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getPreco(),
                produto.getDescricao(),
                produto.getQuantidadeDisponivel(),
                produto.getImagem()
        );
    }

}
