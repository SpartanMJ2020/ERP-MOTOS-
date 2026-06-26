/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Finanzas
 * Objetivo: Mapear la tabla egresos de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "egresos")
public class Egreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_egreso_PK;

    @ManyToOne
    @JoinColumn(name = "id_cuenta_FK")
    private CuentaFinanciera cuenta;

    @ManyToOne
    @JoinColumn(name = "id_empleado_FK")
    private Empleado empleado;

    private String concepto;
    private float monto;
    private String tipo_egreso;   // nomina, compra, servicio, otro
    private String comprobante;

    @Column(name = "fecha_egreso", insertable = false, updatable = false)
    private LocalDateTime fecha_egreso;

    public Egreso() {}

    public int getId_egreso_PK() { return id_egreso_PK; }
    public void setId_egreso_PK(int id_egreso_PK) { this.id_egreso_PK = id_egreso_PK; }

    public CuentaFinanciera getCuenta() { return cuenta; }
    public void setCuenta(CuentaFinanciera cuenta) { this.cuenta = cuenta; }

    public Empleado getEmpleado() { return empleado; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }

    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }

    public float getMonto() { return monto; }
    public void setMonto(float monto) { this.monto = monto; }

    public String getTipo_egreso() { return tipo_egreso; }
    public void setTipo_egreso(String tipo_egreso) { this.tipo_egreso = tipo_egreso; }

    public String getComprobante() { return comprobante; }
    public void setComprobante(String comprobante) { this.comprobante = comprobante; }

    public LocalDateTime getFecha_egreso() { return fecha_egreso; }
    public void setFecha_egreso(LocalDateTime fecha_egreso) { this.fecha_egreso = fecha_egreso; }
}
