/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Empleados
 * Objetivo: Mapear la tabla roles_empleado de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "roles_empleado")
public class RolEmpleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_rol_PK;

    private String nombre;
    private String descripcion;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private LocalDate fecha_creacion;

    public RolEmpleado() {}

    public int getId_rol_PK() { return id_rol_PK; }
    public void setId_rol_PK(int id_rol_PK) { this.id_rol_PK = id_rol_PK; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFecha_creacion() { return fecha_creacion; }
    public void setFecha_creacion(LocalDate fecha_creacion) { this.fecha_creacion = fecha_creacion; }
}
