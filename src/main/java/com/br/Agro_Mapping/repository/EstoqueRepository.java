package com.br.Agro_Mapping.repository;

import com.br.Agro_Mapping.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, UUID> {
}
