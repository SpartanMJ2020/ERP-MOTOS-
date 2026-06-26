/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Inventario
 * Objetivo: Mapear la tabla marcas de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "marcas")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_marca_PK;

    private String nombre;
    private String pais_origen;
    private String descripcion;

    public Marca() {}

    public int getId_marca_PK() { return id_marca_PK; }
    public void setId_marca_PK(int id_marca_PK) { this.id_marca_PK = id_marca_PK; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPais_origen() { return pais_origen; }
    public void setPais_origen(String pais_origen) { this.pais_origen = pais_origen; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
