package com.br.Agro_Mapping.service.mapper;

import com.br.Agro_Mapping.dto.request.ProdutoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ProdutoResponseDTO;
import com.br.Agro_Mapping.model.Produto;
import com.br.Agro_Mapping.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto toProduto(ProdutoRequestDTO produtoRequestDTO, Usuario usuario) {
        Produto produto = Produto.newProduto(
                produtoRequestDTO.nome(),
                produtoRequestDTO.categoria(),
                produtoRequestDTO.preco(),
                produtoRequestDTO.descricao(),
                produtoRequestDTO.imagem()
        );
        produto.setUsuario(usuario);
        return produto;
    }

    public ProdutoResponseDTO toProdutoResponseDTO(Produto produto) {




        return new ProdutoResponseDTO(
                produto.getIdProduto(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getPreco(),
                produto.getDescricao(),
                produto.getImagem()

        );
    }
}
