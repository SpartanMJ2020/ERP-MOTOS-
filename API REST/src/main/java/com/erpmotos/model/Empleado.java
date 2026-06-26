/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Empleados
 * Objetivo: Mapear la tabla empleados de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_empleado_PK;

    @ManyToOne
    @JoinColumn(name = "id_rol_FK")
    private RolEmpleado rol;

    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password_hash;
    private String telefono;
    private String rfc;
    private String direccion;
    private float salario;

    private LocalDate fecha_contrato;

    private boolean activo;

    public Empleado() {}

    public int getId_empleado_PK() { return id_empleado_PK; }
    public void setId_empleado_PK(int id_empleado_PK) { this.id_empleado_PK = id_empleado_PK; }

    public RolEmpleado getRol() { return rol; }
    public void setRol(RolEmpleado rol) { this.rol = rol; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido_paterno() { return apellido_paterno; }
    public void setApellido_paterno(String apellido_paterno) { this.apellido_paterno = apellido_paterno; }

    public String getApellido_materno() { return apellido_materno; }
    public void setApellido_materno(String apellido_materno) { this.apellido_materno = apellido_materno; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword_hash() { return password_hash; }
    public void setPassword_hash(String password_hash) { this.password_hash = password_hash; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getRfc() { return rfc; }
    public void setRfc(String rfc) { this.rfc = rfc; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public float getSalario() { return salario; }
    public void setSalario(float salario) { this.salario = salario; }

    public LocalDate getFecha_contrato() { return fecha_contrato; }
    public void setFecha_contrato(LocalDate fecha_contrato) { this.fecha_contrato = fecha_contrato; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
