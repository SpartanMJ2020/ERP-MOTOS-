/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Ventas
 * Objetivo: Mapear la tabla cotizaciones de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cotizaciones")
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cotizacion_PK;

    @ManyToOne
    @JoinColumn(name = "id_cliente_FK")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_empleado_FK")
    private Empleado empleado;

    private String folio_cotizacion;
    private float total;
    private String estado;        // vigente, aceptada, vencida, rechazada
    private int vigencia_dias;
    private String notas;

    @Column(name = "fecha_cotizacion", insertable = false, updatable = false)
    private LocalDateTime fecha_cotizacion;

    public Cotizacion() {}

    public int getId_cotizacion_PK() { return id_cotizacion_PK; }
    public void setId_cotizacion_PK(int id_cotizacion_PK) { this.id_cotizacion_PK = id_cotizacion_PK; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Empleado getEmpleado() { return empleado; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }

    public String getFolio_cotizacion() { return folio_cotizacion; }
    public void setFolio_cotizacion(String folio_cotizacion) { this.folio_cotizacion = folio_cotizacion; }

    public float getTotal() { return total; }
    public void setTotal(float total) { this.total = total; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getVigencia_dias() { return vigencia_dias; }
    public void setVigencia_dias(int vigencia_dias) { this.vigencia_dias = vigencia_dias; }

    public String getNotas() { return notas; }
    public void setNotas(String notas) { this.notas = notas; }

    public LocalDateTime getFecha_cotizacion() { return fecha_cotizacion; }
    public void setFecha_cotizacion(LocalDateTime fecha_cotizacion) { this.fecha_cotizacion = fecha_cotizacion; }
}
