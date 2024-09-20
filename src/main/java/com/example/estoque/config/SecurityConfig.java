package com.example.estoque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/upload")) // Ignorar CSRF para o endpoint /upload
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/public/**", "/api/**", "/upload", "/produtos").permitAll() // Permitir acesso não autenticado para esses padrões
                        .anyRequest().authenticated() // Requer autenticação para todas as outras URLs
                )
                .formLogin(withDefaults()) // Habilitar login baseado em formulário
                .httpBasic(withDefaults()); // Habilitar autenticação básica

        return http.build(); // Construir o SecurityFilterChain
    }
}
