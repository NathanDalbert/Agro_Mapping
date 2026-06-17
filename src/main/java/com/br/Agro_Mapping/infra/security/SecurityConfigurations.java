package com.br.Agro_Mapping.infra.security;

import lombok.AllArgsConstructor;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;
    private final SecurityHeadersFilter securityHeadersFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/contato").authenticated()
                        .requestMatchers(HttpMethod.GET, "/contato").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/contato/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/contato/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/produto").hasRole("SELLER")
                        .requestMatchers(HttpMethod.POST, "/pedido").authenticated()
                        .requestMatchers(HttpMethod.POST, "/itemPedido").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/produto/**").hasRole("SELLER")
                        .requestMatchers(HttpMethod.DELETE, "/produto/**").hasRole("SELLER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/produto/buscarProdutoPorNome/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/produto/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/feiras").permitAll()
                        .requestMatchers(HttpMethod.GET, "/feiras/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/feiras/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/feiras/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pedido/**").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/pedido/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/estoque/**").authenticated()
                        .requestMatchers("/usuario/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/feiras").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/reserva").authenticated()
                        .requestMatchers(HttpMethod.GET, "/reserva/**").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/reserva/**").authenticated()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(securityHeadersFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Codificador de senha
    }

    // Configuração de CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:5173",
                "http://localhost:5174",
                "http://localhost:3000",
                "http://localhost:9090",
                "http://localhost:8080",
                "http://127.0.0.1:5173",
                "http://127.0.0.1:5174",
                "http://127.0.0.1:9090",
                "http://127.0.0.1:8080"
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept")); // Cabeçalhos permitidos
        configuration.setAllowCredentials(true); // Permite credenciais (cookies, sessões, etc)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplica a configuração a todas as rotas
        return source;
    }
}
