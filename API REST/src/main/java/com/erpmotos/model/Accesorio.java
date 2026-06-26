/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Inventario
 * Objetivo: Mapear la tabla accesorios de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "accesorios")
public class Accesorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_accesorio_PK;

    private String nombre;
    private String descripcion;
    private float precio_costo;
    private float precio_venta;
    private int stock;
    private String categoria;
    private String imagen_url;
    private boolean activo;

    public Accesorio() {}

    public int getId_accesorio_PK() { return id_accesorio_PK; }
    public void setId_accesorio_PK(int id_accesorio_PK) { this.id_accesorio_PK = id_accesorio_PK; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public float getPrecio_costo() { return precio_costo; }
    public void setPrecio_costo(float precio_costo) { this.precio_costo = precio_costo; }

    public float getPrecio_venta() { return precio_venta; }
    public void setPrecio_venta(float precio_venta) { this.precio_venta = precio_venta; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getImagen_url() { return imagen_url; }
    public void setImagen_url(String imagen_url) { this.imagen_url = imagen_url; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
