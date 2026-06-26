package com.erpmotos.dto;

public class DetalleVentaItemDTO {

    private String tipo_producto;
    private int id_producto_ref;
    private int cantidad;
    private float descuento_item;

    public DetalleVentaItemDTO() {}

    public String getTipo_producto() { return tipo_producto; }
    public void setTipo_producto(String tipo_producto) { this.tipo_producto = tipo_producto; }

    public int getId_producto_ref() { return id_producto_ref; }
    public void setId_producto_ref(int id_producto_ref) { this.id_producto_ref = id_producto_ref; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public float getDescuento_item() { return descuento_item; }
    public void setDescuento_item(float descuento_item) { this.descuento_item = descuento_item; }
}
