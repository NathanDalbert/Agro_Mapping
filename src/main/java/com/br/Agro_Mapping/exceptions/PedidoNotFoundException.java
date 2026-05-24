package com.br.Agro_Mapping.exceptions;

import java.util.UUID;

public class PedidoNotFoundException extends RuntimeException {
    public PedidoNotFoundException(UUID id) {
        super("Pedido não encontrado com o ID: " + id);
    }
}
