package com.br.Agro_Mapping.service;

import com.br.Agro_Mapping.dto.request.ReservaRequestDTO;
import com.br.Agro_Mapping.dto.responses.EstoqueResponseDTO;
import com.br.Agro_Mapping.dto.responses.FeiraResponseDTO;
import com.br.Agro_Mapping.dto.responses.ProdutoResponseDTO;
import com.br.Agro_Mapping.dto.responses.ReservaResponseDTO;
import com.br.Agro_Mapping.infra.security.AuthenticatedUserProvider;
import com.br.Agro_Mapping.model.Estoque;
import com.br.Agro_Mapping.model.Feira;
import com.br.Agro_Mapping.model.Produto;
import com.br.Agro_Mapping.model.Reserva;
import com.br.Agro_Mapping.model.Usuario;
import com.br.Agro_Mapping.model.enuns.ReservaStatus;
import com.br.Agro_Mapping.repository.ProdutoRepository;
import com.br.Agro_Mapping.repository.ReservaRepository;
import com.br.Agro_Mapping.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservaService implements ReservaServiceInterface {

    private final ReservaRepository reservaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final AuthenticatedUserProvider authUserProvider;

    @Transactional
    public ReservaResponseDTO criarReserva(ReservaRequestDTO dto) {
        UUID authenticatedId = authUserProvider.getAuthenticatedUserId();
        if (!authenticatedId.equals(dto.idUsuario())) {
            throw new RuntimeException("Você só pode criar reservas para sua própria conta.");
        }

        Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));

        Produto produto = produtoRepository.findById(dto.idProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        boolean reservaAtiva = reservaRepository.existsByUsuarioAndProdutoAndStatusIn(
                usuario, produto, List.of(ReservaStatus.PENDENTE, ReservaStatus.CONFIRMADA));
        if (reservaAtiva) {
            throw new RuntimeException("Você já possui uma reserva ativa para este produto.");
        }

        Estoque estoque = produto.getEstoque();
        if (estoque == null || estoque.getQuantidadeDisponivel() < dto.quantidade()) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
        }

        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setProduto(produto);
        reserva.setFeira(produto.getFeira());
        reserva.setQuantidade(dto.quantidade());
        reserva.setStatus(ReservaStatus.PENDENTE);
        reserva.setDataReserva(LocalDate.now());
        reserva.setQrCodeHash(UUID.randomUUID().toString());

        return toDto(reservaRepository.save(reserva));
    }

    @Transactional(readOnly = true)
    public List<ReservaResponseDTO> listarPorUsuario(UUID idUsuario) {
        UUID authenticatedId = authUserProvider.getAuthenticatedUserId();
        if (!authenticatedId.equals(idUsuario)) {
            throw new RuntimeException("Você só pode visualizar suas próprias reservas.");
        }
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
        return reservaRepository.findByUsuario(usuario).stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ReservaResponseDTO> listarPorFeira(UUID idFeira) {
        return reservaRepository.findByFeira_IdFeira(idFeira).stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ReservaResponseDTO> listarPorVendedor() {
        UUID vendedorId = authUserProvider.getAuthenticatedUserId();
        return reservaRepository.findByProduto_Usuario_IdUsuario(vendedorId).stream()
                .filter(r -> r.getStatus() == ReservaStatus.PENDENTE || r.getStatus() == ReservaStatus.CONFIRMADA)
                .map(this::toDto)
                .toList();
    }

    @Transactional
    public ReservaResponseDTO atualizarStatus(UUID idReserva, String statusStr) {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada."));
        reserva.setStatus(ReservaStatus.valueOf(statusStr));
        return toDto(reservaRepository.save(reserva));
    }

    private ReservaResponseDTO toDto(Reserva r) {
        Produto p = r.getProduto();
        Estoque e = p.getEstoque();
        EstoqueResponseDTO estoqueDto = e != null
                ? new EstoqueResponseDTO(e.getIdEstoque(), p.getIdProduto(), e.getQuantidadeDisponivel())
                : null;
        ProdutoResponseDTO produtoDto = new ProdutoResponseDTO(
                p.getIdProduto(), p.getNome(), p.getCategoria(), p.getPreco(),
                p.getDescricao(), p.getImagem(),
                p.getUsuario() != null ? p.getUsuario().getIdUsuario() : null,
                estoqueDto);

        Feira f = r.getFeira();
        FeiraResponseDTO feiraDto = f != null
                ? new FeiraResponseDTO(f.getIdFeira(), f.getNome(), f.getLocalizacao(),
                        f.getDataFuncionamento(), f.getLatitude(), f.getLongitude())
                : null;

        return new ReservaResponseDTO(
                r.getIdReserva(), r.getStatus().name(), r.getDataReserva(),
                r.getQrCodeHash(), r.getQuantidade(), produtoDto, feiraDto);
    }
}
