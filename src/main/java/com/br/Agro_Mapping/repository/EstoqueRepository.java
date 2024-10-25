package com.br.Agro_Mapping.repository;

import com.br.Agro_Mapping.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EstoqueRepository extends JpaRepository<Estoque, UUID> {
}
