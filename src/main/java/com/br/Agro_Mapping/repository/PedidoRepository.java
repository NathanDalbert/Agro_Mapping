package com.br.Agro_Mapping.repository;

import com.br.Agro_Mapping.model.Pedido;
import com.br.Agro_Mapping.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    List<Pedido> findByUsuario(Usuario usuario);

}
