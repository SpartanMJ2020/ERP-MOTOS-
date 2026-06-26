package com.erpmotos.dto;

import java.util.List;
import com.erpmotos.model.Cliente;
import com.erpmotos.model.Empleado;

public class VentaConDetalleDTO {

    private String folio;
    private Cliente cliente;
    private Empleado empleado;
    private float descuento;
    private String metodo_pago;
    private String estado;
    private String observaciones;
    private List<DetalleVentaItemDTO> detalles;

    public VentaConDetalleDTO() {}

    public String getFolio() { return folio; }
    public void setFolio(String folio) { this.folio = folio; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Empleado getEmpleado() { return empleado; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }

    public float getDescuento() { return descuento; }
    public void setDescuento(float descuento) { this.descuento = descuento; }

    public String getMetodo_pago() { return metodo_pago; }
    public void setMetodo_pago(String metodo_pago) { this.metodo_pago = metodo_pago; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public List<DetalleVentaItemDTO> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleVentaItemDTO> detalles) { this.detalles = detalles; }
}
