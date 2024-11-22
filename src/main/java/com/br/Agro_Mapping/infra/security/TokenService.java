package com.br.Agro_Mapping.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
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
            String token = JWT.create()
                    .withIssuer("Agro Mapping")
                    .withSubject(usuario.getEmail())
                    .withClaim("idUsuario", usuario.getIdUsuario().toString()) // Adiciona o ID do usuário como claim
                    .withClaim("role", usuario.getUserRole().ordinal()) // Adiciona a role do usuário como claim
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    // Método para validar o token e extrair o email
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String email = JWT.require(algorithm)
                    .withIssuer("Agro Mapping")
                    .build()
                    .verify(token)
                    .getSubject();

            return email;
        } catch (JWTVerificationException e) {
            System.out.println("Erro ao validar o token: " + e.getMessage());
            return "";
        }
    }

    // Método para extrair o ID do usuário a partir do token
    public UUID extractUserId(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String idUsuario = JWT.require(algorithm)
                    .withIssuer("Agro Mapping")
                    .build()
                    .verify(token)
                    .getClaim("idUsuario")
                    .asString(); // Extrai o ID como String
            return UUID.fromString(idUsuario); // Converte para UUID
        } catch (JWTVerificationException e) {
            System.out.println("Erro ao extrair o ID do usuário: " + e.getMessage());
            throw new RuntimeException("Token inválido ou expirado.");
        }
    }

    // Método para extrair a role do usuário a partir do token
    public String extractUserRole(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String role = JWT.require(algorithm)
                    .withIssuer("Agro Mapping")
                    .build()
                    .verify(token)
                    .getClaim("role")
                    .asString(); // Extrai a role como String
            return role; // Retorna a role
        } catch (JWTVerificationException e) {
            System.out.println("Erro ao extrair a role do usuário: " + e.getMessage());
            throw new RuntimeException("Token inválido ou expirado.");
        }
    }

    // Método para gerar a data de expiração do token
    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
