/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Finanzas
 * Objetivo: Mapear la tabla cuentas_financieras de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cuentas_financieras")
public class CuentaFinanciera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cuenta_PK;

    private String nombre;
    private String tipo;       // banco, caja, credito
    private float saldo;
    private String descripcion;
    private boolean activo;

    public CuentaFinanciera() {}

    public int getId_cuenta_PK() { return id_cuenta_PK; }
    public void setId_cuenta_PK(int id_cuenta_PK) { this.id_cuenta_PK = id_cuenta_PK; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public float getSaldo() { return saldo; }
    public void setSaldo(float saldo) { this.saldo = saldo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
