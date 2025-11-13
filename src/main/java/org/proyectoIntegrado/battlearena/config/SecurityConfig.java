package org.proyectoIntegrado.battlearena.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // desactivar CSRF en desarrollo, si no tu POST peta
                .cors(cors -> {}) // habilita CORS con configuración por defecto
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/usuario/crear").permitAll()
                        .requestMatchers("/usuario/login").permitAll()
                        .anyRequest().permitAll() // lo demás pide login
                );

        return http.build();
    }
}