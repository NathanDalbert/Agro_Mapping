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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/contato").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/produto").hasRole("SELLER")
                        .requestMatchers(HttpMethod.POST, "/pedido").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/pedido").hasRole("SELLER")
                        .requestMatchers(HttpMethod.POST, "/itemPedido").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/itemPedido").hasRole("SELLER")
                        .requestMatchers(HttpMethod.PUT, "/produto/**").hasRole("SELLER")
                        .requestMatchers(HttpMethod.GET, "/usuario/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/usuario/**").hasRole("SELLER")
                        .requestMatchers(HttpMethod.DELETE, "/produto/**").hasRole("SELLER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/usuario/**").hasRole("USER")
                        .requestMatchers("/contato").permitAll()
                        .requestMatchers(HttpMethod.GET, "/produto/buscarProdutoPorNome/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/feiras").permitAll()
                        .requestMatchers(HttpMethod.GET, "/pedido").hasRole("SELLER")
                        .requestMatchers(HttpMethod.GET, "/pedido").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/feiras").hasRole("ADMIN")
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // Acesso ao Swagger
                        .anyRequest().authenticated()) // Qualquer outra requisição requer autenticação
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // Filtro de segurança
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
        configuration.setAllowedOrigins(Arrays.asList("https://agro-mapping-front.vercel.app/")); // Adiciona a origem do frontend corretamente
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Inclui "OPTIONS" para requisição prévia
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept")); // Cabeçalhos permitidos
        configuration.setAllowCredentials(true); // Permite credenciais (cookies, sessões, etc)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplica a configuração a todas as rotas
        return source;
    }
}
