package com.br.Agro_Mapping.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.br.Agro_Mapping.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Service
public class TokenService {

    @Value("${agro-mapping.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Agro Mapping")
                    .withSubject(usuario.getEmail())
                    .withClaim("idUsuario", usuario.getIdUsuario().toString()) // ID do usuário
                    .withClaim("role", usuario.getUserRole().name()) // Nome da role no token
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            DecodedJWT decodedJWT = JWT.require(algorithm)
                    .withIssuer("Agro Mapping")
                    .build()
                    .verify(token);
            return decodedJWT.getSubject(); // Retorna o email do usuário
        } catch (JWTVerificationException e) {
            System.out.println("Erro ao validar o token: " + e.getMessage());
            return null;
        }
    }

    public UUID extractUserId(String token) {
        try {
            DecodedJWT decodedJWT = decodeToken(token);
            String idUsuario = decodedJWT.getClaim("idUsuario").asString();
            return UUID.fromString(idUsuario); // Converte para UUID
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("ID do usuário inválido no token.", e);
        }
    }

    public String extractUserRole(String token) {
        try {
            DecodedJWT decodedJWT = decodeToken(token);
            return decodedJWT.getClaim("role").asString(); // Extrai a role
        } catch (Exception e) {
            throw new RuntimeException("Erro ao extrair a role do token.", e);
        }
    }

    private DecodedJWT decodeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Agro Mapping")
                    .build()
                    .verify(token);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token inválido ou expirado.", e);
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
