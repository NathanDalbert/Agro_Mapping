package com.br.Agro_Mapping.repository;

import com.br.Agro_Mapping.model.Contato;
import com.br.Agro_Mapping.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    List<Usuario> findByNome(String nome);

    @Query("SELECT u FROM usuario u LEFT JOIN FETCH u.contatos WHERE u.idUsuario = :id")
    Usuario findByIdFetchContatos(@Param("id") UUID id);





}
