/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Ventas
 * Objetivo: Mapear la tabla detalle_ventas de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_ventas")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detalle_PK;

    @ManyToOne
    @JoinColumn(name = "id_venta_FK")
    private Venta venta;

    private String tipo_producto;   // moto o accesorio
    private int id_producto_ref;
    private int cantidad;
    private float precio_unitario;
    private float descuento_item;
    private float subtotal_item;

    public DetalleVenta() {}

    public int getId_detalle_PK() { return id_detalle_PK; }
    public void setId_detalle_PK(int id_detalle_PK) { this.id_detalle_PK = id_detalle_PK; }

    public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }

    public String getTipo_producto() { return tipo_producto; }
    public void setTipo_producto(String tipo_producto) { this.tipo_producto = tipo_producto; }

    public int getId_producto_ref() { return id_producto_ref; }
    public void setId_producto_ref(int id_producto_ref) { this.id_producto_ref = id_producto_ref; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public float getPrecio_unitario() { return precio_unitario; }
    public void setPrecio_unitario(float precio_unitario) { this.precio_unitario = precio_unitario; }

    public float getDescuento_item() { return descuento_item; }
    public void setDescuento_item(float descuento_item) { this.descuento_item = descuento_item; }

    public float getSubtotal_item() { return subtotal_item; }
    public void setSubtotal_item(float subtotal_item) { this.subtotal_item = subtotal_item; }
}
