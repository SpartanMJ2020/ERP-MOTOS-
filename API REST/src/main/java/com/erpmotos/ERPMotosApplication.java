/* Universidad Politécnica de Francisco I. Madero
 * Carrera: Ingeniería en Sistemas Computacionales
 * Proyecto: ERP de Venta de Motos
 * Fecha: Junio 2026
 * Objetivo: Clase principal que arranca la aplicación Spring Boot del ERP. */
package com.erpmotos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ERPMotosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ERPMotosApplication.class, args);
    }
}