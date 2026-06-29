package com.erpmotos.dto;

public class StockBajoDTO {

    private String tipo_producto;
    private int id_producto;
    private String nombre;
    private int stock;

    public StockBajoDTO() {}

    public StockBajoDTO(String tipo_producto, int id_producto, String nombre, int stock) {
        this.tipo_producto = tipo_producto;
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.stock = stock;
    }

    public String getTipo_producto() { return tipo_producto; }
    public void setTipo_producto(String tipo_producto) { this.tipo_producto = tipo_producto; }

    public int getId_producto() { return id_producto; }
    public void setId_producto(int id_producto) { this.id_producto = id_producto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
