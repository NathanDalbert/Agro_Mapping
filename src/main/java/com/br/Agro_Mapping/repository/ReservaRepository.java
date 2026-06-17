package com.br.Agro_Mapping.repository;

import com.br.Agro_Mapping.model.Produto;
import com.br.Agro_Mapping.model.Reserva;
import com.br.Agro_Mapping.model.Usuario;
import com.br.Agro_Mapping.model.enuns.ReservaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReservaRepository extends JpaRepository<Reserva, UUID> {
    List<Reserva> findByUsuario(Usuario usuario);
    boolean existsByUsuarioAndProdutoAndStatusIn(Usuario usuario, Produto produto, List<ReservaStatus> statuses);
    List<Reserva> findByFeira_IdFeira(UUID idFeira);
    List<Reserva> findByProduto_Usuario_IdUsuario(UUID idUsuario);
}
