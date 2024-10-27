package com.br.Agro_Mapping.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF para APIs REST
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Utiliza sessão sem estado
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // Permite acesso livre ao login
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll() // Permite acesso livre ao registro
                        .requestMatchers(HttpMethod.GET, "/produto/**").permitAll() // Permite acesso livre a produtos
                        .requestMatchers(HttpMethod.POST, "/produto").hasRole("SELLER") // Acesso restrito a vendedores
                        .requestMatchers(HttpMethod.PUT, "/produto/**").hasRole("SELLER") // Atualizações apenas para vendedores
                        .requestMatchers(HttpMethod.DELETE, "/produto/**").hasRole("SELLER") // Deleções apenas para vendedores
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Acesso restrito a admin
                        .requestMatchers("/user/**").hasRole("USER") // Acesso restrito a usuários normais
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Permite acesso ao Swagger
                        .anyRequest().authenticated()) // Qualquer outra requisição requer autenticação
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona o filtro de segurança
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Configura o encoder de senha
    }
}
