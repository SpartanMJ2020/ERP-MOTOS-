/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Inventario
 * Objetivo: Mapear la tabla movimientos_inventario de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_inventario")
public class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_movimiento_PK;

    private String tipo_producto;     // 'moto' o 'accesorio'
    private int id_producto_ref;

    @ManyToOne
    @JoinColumn(name = "id_empleado_FK")
    private Empleado empleado;

    private String tipo_movimiento;   // 'entrada', 'salida', 'ajuste'
    private int cantidad;
    private String motivo;

    @Column(name = "fecha_movimiento", insertable = false, updatable = false)
    private LocalDateTime fecha_movimiento;

    public MovimientoInventario() {}

    public int getId_movimiento_PK() { return id_movimiento_PK; }
    public void setId_movimiento_PK(int id_movimiento_PK) { this.id_movimiento_PK = id_movimiento_PK; }

    public String getTipo_producto() { return tipo_producto; }
    public void setTipo_producto(String tipo_producto) { this.tipo_producto = tipo_producto; }

    public int getId_producto_ref() { return id_producto_ref; }
    public void setId_producto_ref(int id_producto_ref) { this.id_producto_ref = id_producto_ref; }

    public Empleado getEmpleado() { return empleado; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }

    public String getTipo_movimiento() { return tipo_movimiento; }
    public void setTipo_movimiento(String tipo_movimiento) { this.tipo_movimiento = tipo_movimiento; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public LocalDateTime getFecha_movimiento() { return fecha_movimiento; }
    public void setFecha_movimiento(LocalDateTime fecha_movimiento) { this.fecha_movimiento = fecha_movimiento; }
}
