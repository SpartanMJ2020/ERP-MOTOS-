/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Inventario
 * Objetivo: Mapear la tabla provedor de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "provedor")
public class Provedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_provedor_PK;

    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String email;
    private String telefono;
    private String rfc;

    @ManyToOne
    @JoinColumn(name = "moto_FK")
    private Moto moto;

    @ManyToOne
    @JoinColumn(name = "accesorio_FK")
    private Accesorio accesorio;

    public Provedor() {}

    public int getId_provedor_PK() { return id_provedor_PK; }
    public void setId_provedor_PK(int id_provedor_PK) { this.id_provedor_PK = id_provedor_PK; }

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

    public Moto getMoto() { return moto; }
    public void setMoto(Moto moto) { this.moto = moto; }

    public Accesorio getAccesorio() { return accesorio; }
    public void setAccesorio(Accesorio accesorio) { this.accesorio = accesorio; }
}
