/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Finanzas
 * Objetivo: Mapear la tabla creditos_cliente de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "creditos_cliente")
public class CreditoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_credito_PK;

    @ManyToOne
    @JoinColumn(name = "id_cliente_FK")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_venta_FK")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_empleado_FK")
    private Empleado empleado;

    private float monto_total;
    private float monto_pagado;
    private float saldo_pendiente;
    private float tasa_interes;
    private int num_pagos;
    private String periodicidad;   // semanal, quincenal, mensual
    private String estado;         // activo, liquidado, vencido

    private LocalDate fecha_inicio;
    private LocalDate fecha_limite;

    public CreditoCliente() {}

    public int getId_credito_PK() { return id_credito_PK; }
    public void setId_credito_PK(int id_credito_PK) { this.id_credito_PK = id_credito_PK; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }

    public Empleado getEmpleado() { return empleado; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }

    public float getMonto_total() { return monto_total; }
    public void setMonto_total(float monto_total) { this.monto_total = monto_total; }

    public float getMonto_pagado() { return monto_pagado; }
    public void setMonto_pagado(float monto_pagado) { this.monto_pagado = monto_pagado; }

    public float getSaldo_pendiente() { return saldo_pendiente; }
    public void setSaldo_pendiente(float saldo_pendiente) { this.saldo_pendiente = saldo_pendiente; }

    public float getTasa_interes() { return tasa_interes; }
    public void setTasa_interes(float tasa_interes) { this.tasa_interes = tasa_interes; }

    public int getNum_pagos() { return num_pagos; }
    public void setNum_pagos(int num_pagos) { this.num_pagos = num_pagos; }

    public String getPeriodicidad() { return periodicidad; }
    public void setPeriodicidad(String periodicidad) { this.periodicidad = periodicidad; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getFecha_inicio() { return fecha_inicio; }
    public void setFecha_inicio(LocalDate fecha_inicio) { this.fecha_inicio = fecha_inicio; }

    public LocalDate getFecha_limite() { return fecha_limite; }
    public void setFecha_limite(LocalDate fecha_limite) { this.fecha_limite = fecha_limite; }
}
