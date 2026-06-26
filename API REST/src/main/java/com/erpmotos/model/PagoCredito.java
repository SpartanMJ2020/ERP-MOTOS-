/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Finanzas
 * Objetivo: Mapear la tabla pagos_credito de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos_credito")
public class PagoCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pago_PK;

    @ManyToOne
    @JoinColumn(name = "id_credito_FK")
    private CreditoCliente credito;

    @ManyToOne
    @JoinColumn(name = "id_cuenta_FK")
    private CuentaFinanciera cuenta;

    private float monto_pago;
    private int num_pago;
    private String observaciones;

    @Column(name = "fecha_pago", insertable = false, updatable = false)
    private LocalDateTime fecha_pago;

    public PagoCredito() {}

    public int getId_pago_PK() { return id_pago_PK; }
    public void setId_pago_PK(int id_pago_PK) { this.id_pago_PK = id_pago_PK; }

    public CreditoCliente getCredito() { return credito; }
    public void setCredito(CreditoCliente credito) { this.credito = credito; }

    public CuentaFinanciera getCuenta() { return cuenta; }
    public void setCuenta(CuentaFinanciera cuenta) { this.cuenta = cuenta; }

    public float getMonto_pago() { return monto_pago; }
    public void setMonto_pago(float monto_pago) { this.monto_pago = monto_pago; }

    public int getNum_pago() { return num_pago; }
    public void setNum_pago(int num_pago) { this.num_pago = num_pago; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public LocalDateTime getFecha_pago() { return fecha_pago; }
    public void setFecha_pago(LocalDateTime fecha_pago) { this.fecha_pago = fecha_pago; }
}
