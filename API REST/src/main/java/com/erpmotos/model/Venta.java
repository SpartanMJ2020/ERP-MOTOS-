/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Ventas
 * Objetivo: Mapear la tabla ventas de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_venta_PK;

    @ManyToOne
    @JoinColumn(name = "id_cliente_FK")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_empleado_FK")
    private Empleado empleado;

    private String folio;
    private float subtotal;
    private float descuento;
    private float iva;
    private float total;
    private String metodo_pago;   // contado, credito, transferencia, tarjeta
    private String estado;        // pendiente, completada, cancelada
    private String observaciones;

    @Column(name = "fecha_venta", insertable = false, updatable = false)
    private LocalDateTime fecha_venta;

    public Venta() {}

    public int getId_venta_PK() { return id_venta_PK; }
    public void setId_venta_PK(int id_venta_PK) { this.id_venta_PK = id_venta_PK; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Empleado getEmpleado() { return empleado; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }

    public String getFolio() { return folio; }
    public void setFolio(String folio) { this.folio = folio; }

    public float getSubtotal() { return subtotal; }
    public void setSubtotal(float subtotal) { this.subtotal = subtotal; }

    public float getDescuento() { return descuento; }
    public void setDescuento(float descuento) { this.descuento = descuento; }

    public float getIva() { return iva; }
    public void setIva(float iva) { this.iva = iva; }

    public float getTotal() { return total; }
    public void setTotal(float total) { this.total = total; }

    public String getMetodo_pago() { return metodo_pago; }
    public void setMetodo_pago(String metodo_pago) { this.metodo_pago = metodo_pago; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public LocalDateTime getFecha_venta() { return fecha_venta; }
    public void setFecha_venta(LocalDateTime fecha_venta) { this.fecha_venta = fecha_venta; }
}
