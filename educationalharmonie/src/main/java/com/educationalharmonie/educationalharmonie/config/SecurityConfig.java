package com.educationalharmonie.educationalharmonie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @SuppressWarnings("deprecation")
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(requests -> requests
                .requestMatchers("/public/**").permitAll() // Permitir acceso público a rutas específicas
                .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
            )
            .formLogin(login -> login
                .loginPage("/login") // Ruta personalizada para el formulario de inicio de sesión
                .permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            );
    }
}


