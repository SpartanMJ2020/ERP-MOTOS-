/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Finanzas
 * Objetivo: Mapear la tabla ingresos de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ingresos")
public class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ingreso_PK;

    @ManyToOne
    @JoinColumn(name = "id_cuenta_FK")
    private CuentaFinanciera cuenta;

    @ManyToOne
    @JoinColumn(name = "id_venta_FK")
    private Venta venta;

    private String concepto;
    private float monto;
    private String tipo_ingreso;   // venta, abono, otro
    private String comprobante;

    @Column(name = "fecha_ingreso", insertable = false, updatable = false)
    private LocalDateTime fecha_ingreso;

    public Ingreso() {}

    public int getId_ingreso_PK() { return id_ingreso_PK; }
    public void setId_ingreso_PK(int id_ingreso_PK) { this.id_ingreso_PK = id_ingreso_PK; }

    public CuentaFinanciera getCuenta() { return cuenta; }
    public void setCuenta(CuentaFinanciera cuenta) { this.cuenta = cuenta; }

    public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }

    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }

    public float getMonto() { return monto; }
    public void setMonto(float monto) { this.monto = monto; }

    public String getTipo_ingreso() { return tipo_ingreso; }
    public void setTipo_ingreso(String tipo_ingreso) { this.tipo_ingreso = tipo_ingreso; }

    public String getComprobante() { return comprobante; }
    public void setComprobante(String comprobante) { this.comprobante = comprobante; }

    public LocalDateTime getFecha_ingreso() { return fecha_ingreso; }
    public void setFecha_ingreso(LocalDateTime fecha_ingreso) { this.fecha_ingreso = fecha_ingreso; }
}
