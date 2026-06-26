/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Inventario
 * Objetivo: Mapear la tabla motos de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "motos")
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_moto_PK;

    @ManyToOne
    @JoinColumn(name = "id_marca_FK")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "id_categoria_FK")
    private CategoriaMoto categoria;

    private String modelo;
    private int anio;
    private int cilindraje;
    private String color;
    private float precio_costo;
    private float precio_venta;
    private int stock;
    private String num_serie;
    private String imagen_url;
    private String descripcion;
    private boolean activo;

    @Column(name = "fecha_alta", insertable = false, updatable = false)
    private LocalDate fecha_alta;

    public Moto() {}

    public int getId_moto_PK() { return id_moto_PK; }
    public void setId_moto_PK(int id_moto_PK) { this.id_moto_PK = id_moto_PK; }

    public Marca getMarca() { return marca; }
    public void setMarca(Marca marca) { this.marca = marca; }

    public CategoriaMoto getCategoria() { return categoria; }
    public void setCategoria(CategoriaMoto categoria) { this.categoria = categoria; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }

    public int getCilindraje() { return cilindraje; }
    public void setCilindraje(int cilindraje) { this.cilindraje = cilindraje; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public float getPrecio_costo() { return precio_costo; }
    public void setPrecio_costo(float precio_costo) { this.precio_costo = precio_costo; }

    public float getPrecio_venta() { return precio_venta; }
    public void setPrecio_venta(float precio_venta) { this.precio_venta = precio_venta; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getNum_serie() { return num_serie; }
    public void setNum_serie(String num_serie) { this.num_serie = num_serie; }

    public String getImagen_url() { return imagen_url; }
    public void setImagen_url(String imagen_url) { this.imagen_url = imagen_url; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public LocalDate getFecha_alta() { return fecha_alta; }
    public void setFecha_alta(LocalDate fecha_alta) { this.fecha_alta = fecha_alta; }
}
