package com.erpmotos.dto;

import java.util.List;

public class AlertaStockResponse {

    private int umbral;
    private int total;
    private List<StockBajoDTO> productos;

    public AlertaStockResponse() {}

    public AlertaStockResponse(int umbral, int total, List<StockBajoDTO> productos) {
        this.umbral = umbral;
        this.total = total;
        this.productos = productos;
    }

    public int getUmbral() { return umbral; }
    public void setUmbral(int umbral) { this.umbral = umbral; }

    public int getTotal() { return total; }
    public void setTotal(int total) { this.total = total; }

    public List<StockBajoDTO> getProductos() { return productos; }
    public void setProductos(List<StockBajoDTO> productos) { this.productos = productos; }
}
