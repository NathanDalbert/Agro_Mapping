package com.br.Agro_Mapping.repository;

import com.br.Agro_Mapping.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContatoRepository extends JpaRepository<Contato, UUID> {
}
