package com.br.Agro_Mapping.repository;

import com.br.Agro_Mapping.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
