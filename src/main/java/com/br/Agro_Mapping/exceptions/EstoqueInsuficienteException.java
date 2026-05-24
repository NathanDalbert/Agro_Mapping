package com.br.Agro_Mapping.exceptions;

public class EstoqueInsuficienteException extends RuntimeException {
    public EstoqueInsuficienteException(String produtoNome) {
        super("Estoque insuficiente para o produto: " + produtoNome);
    }
}
