package com.br.Agro_Mapping.repository;

import com.br.Agro_Mapping.model.ItemPedido;
import com.br.Agro_Mapping.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, UUID> {
    List<ItemPedido> findByUsuario(Usuario usuario);
}
