package com.br.Agro_Mapping.infra;

import com.br.Agro_Mapping.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {

    // Handler para UsuarioNotFoundException
    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<Object> handleUsuarioNotFoundException(UsuarioNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    // Handler para EmailAlreadyExistsException
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Object> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT, request);
    }

    // Handler para UserUnderageException
    @ExceptionHandler(UserUnderageException.class)
    public ResponseEntity<Object> handleUserUnderageException(UserUnderageException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request);
    }

    // Handler para ContatoNotFoundException
    @ExceptionHandler(ContatoNotFoundException.class)
    public ResponseEntity<Object> handleContatoNotFoundException(ContatoNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    // Handler para ProdutoNotFoundException
    @ExceptionHandler(ProdutoNotFoundException.class)
    public ResponseEntity<Object> handleProdutoNotFoundException(ProdutoNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    // Handler para PedidoNotFoundException
    @ExceptionHandler(PedidoNotFoundException.class)
    public ResponseEntity<Object> handlePedidoNotFoundException(PedidoNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    // Handler para EstoqueNotFoundException
    @ExceptionHandler(EstoqueNotFoundException.class)
    public ResponseEntity<Object> handleEstoqueNotFoundException(EstoqueNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    // Handler para FeiraNotFoundException
    @ExceptionHandler(FeiraNotFoundException.class)
    public ResponseEntity<Object> handleFeiraNotFoundException(FeiraNotFoundException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request);
    }

    // Handler para EstoqueInsuficienteException
    @ExceptionHandler(EstoqueInsuficienteException.class)
    public ResponseEntity<Object> handleEstoqueInsuficienteException(EstoqueInsuficienteException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT, request);
    }

    // Handler para UnauthorizedOperationException
    @ExceptionHandler(UnauthorizedOperationException.class)
    public ResponseEntity<Object> handleUnauthorizedOperationException(UnauthorizedOperationException ex, WebRequest request) {
        return buildErrorResponse(ex.getMessage(), HttpStatus.FORBIDDEN, request);
    }

    // Handler genérico para RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {
        return buildErrorResponse("Erro interno no servidor", HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    // Método genérico para construir respostas de erro
    private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(body, status);
    }
}
