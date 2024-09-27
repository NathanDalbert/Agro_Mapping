package com.br.Agro_Mapping.controller;

import com.br.Agro_Mapping.controller.swagger.ProdutoSwagger;
import com.br.Agro_Mapping.dto.request.ProdutoRequestDTO;
import com.br.Agro_Mapping.dto.responses.ProdutoResponseDTO;
import com.br.Agro_Mapping.service.ProdutoServiceInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produto")
public class ProdutoController implements ProdutoSwagger {

    private final ProdutoServiceInterface produtoServiceInterface;


    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criarProduto(@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        ProdutoResponseDTO produtoResponseDTO = produtoServiceInterface.criarProduto(produtoRequestDTO);
        return ResponseEntity.ok(produtoResponseDTO);
    }


    @GetMapping("/")
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos() {
        List<ProdutoResponseDTO> produtos = produtoServiceInterface.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> buscarPorNome(@RequestParam String nome) {
        List<ProdutoResponseDTO> produto = produtoServiceInterface.findByName(nome);
        return ResponseEntity.ok(produto);

    }


    @PutMapping
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable UUID id,
                                                               @Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        ProdutoResponseDTO produtoAtualizado = produtoServiceInterface.atualizarProduto(id, produtoRequestDTO);
        return ResponseEntity.ok(produtoAtualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable UUID id) {
        produtoServiceInterface.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscarProdutoPorNome/nome/{nome}")
    public ResponseEntity<List<ProdutoResponseDTO>> buscarProdutoPorNome(@PathVariable("nome") String nome) {
        List<ProdutoResponseDTO> produtos = produtoServiceInterface.findByName(nome);
        return ResponseEntity.ok(produtos);
    }

}
