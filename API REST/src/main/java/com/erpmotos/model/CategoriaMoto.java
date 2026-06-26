/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Inventario
 * Objetivo: Mapear la tabla categorias_moto de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categorias_moto")
public class CategoriaMoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_categoria_PK;

    private String nombre;
    private String descripcion;

    public CategoriaMoto() {}

    public int getId_categoria_PK() { return id_categoria_PK; }
    public void setId_categoria_PK(int id_categoria_PK) { this.id_categoria_PK = id_categoria_PK; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
