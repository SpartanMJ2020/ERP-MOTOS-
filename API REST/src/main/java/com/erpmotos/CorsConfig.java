/* Universidad Politécnica de Francisco I. Madero
 * Carrera: Ingeniería en Sistemas Computacionales
 * Proyecto: ERP de Venta de Motos
 * Fecha: Junio 2026
 * Objetivo: Configuración global de CORS para permitir peticiones
 *           desde el frontend (Live Server) hacia la API REST. */
package com.erpmotos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Permitir cualquier origen (Live Server, navegador directo, etc.)
        config.addAllowedOriginPattern("*");

        // Métodos HTTP permitidos
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");

        // Permitir todos los headers (incluyendo Content-Type que usa el frontend)
        config.addAllowedHeader("*");

        // Aplicar esta configuración a todos los endpoints de la API
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);

        return new CorsFilter(source);
    }
}