/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Clientes
 * Objetivo: Mapear la tabla clientes de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cliente_PK;

    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String email;
    private String telefono;
    private String rfc;

    private LocalDate fecha_nacimiento;

    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private LocalDateTime fecha_registro;

    private boolean activo;

    public Cliente() {}

    public int getId_cliente_PK() { return id_cliente_PK; }
    public void setId_cliente_PK(int id_cliente_PK) { this.id_cliente_PK = id_cliente_PK; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido_paterno() { return apellido_paterno; }
    public void setApellido_paterno(String apellido_paterno) { this.apellido_paterno = apellido_paterno; }

    public String getApellido_materno() { return apellido_materno; }
    public void setApellido_materno(String apellido_materno) { this.apellido_materno = apellido_materno; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getRfc() { return rfc; }
    public void setRfc(String rfc) { this.rfc = rfc; }

    public LocalDate getFecha_nacimiento() { return fecha_nacimiento; }
    public void setFecha_nacimiento(LocalDate fecha_nacimiento) { this.fecha_nacimiento = fecha_nacimiento; }

    public LocalDateTime getFecha_registro() { return fecha_registro; }
    public void setFecha_registro(LocalDateTime fecha_registro) { this.fecha_registro = fecha_registro; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
