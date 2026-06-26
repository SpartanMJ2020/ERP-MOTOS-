/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Ventas
 * Objetivo: Mapear la tabla detalle_cotizaciones de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_cotizaciones")
public class DetalleCotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_det_cot_PK;

    @ManyToOne
    @JoinColumn(name = "id_cotizacion_FK")
    private Cotizacion cotizacion;

    private String tipo_producto;   // moto o accesorio
    private int id_producto_ref;
    private int cantidad;
    private float precio_unitario;
    private float subtotal_item;

    public DetalleCotizacion() {}

    public int getId_det_cot_PK() { return id_det_cot_PK; }
    public void setId_det_cot_PK(int id_det_cot_PK) { this.id_det_cot_PK = id_det_cot_PK; }

    public Cotizacion getCotizacion() { return cotizacion; }
    public void setCotizacion(Cotizacion cotizacion) { this.cotizacion = cotizacion; }

    public String getTipo_producto() { return tipo_producto; }
    public void setTipo_producto(String tipo_producto) { this.tipo_producto = tipo_producto; }

    public int getId_producto_ref() { return id_producto_ref; }
    public void setId_producto_ref(int id_producto_ref) { this.id_producto_ref = id_producto_ref; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public float getPrecio_unitario() { return precio_unitario; }
    public void setPrecio_unitario(float precio_unitario) { this.precio_unitario = precio_unitario; }

    public float getSubtotal_item() { return subtotal_item; }
    public void setSubtotal_item(float subtotal_item) { this.subtotal_item = subtotal_item; }
}
