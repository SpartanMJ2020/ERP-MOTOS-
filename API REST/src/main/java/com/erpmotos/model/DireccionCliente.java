/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Clientes
 * Objetivo: Mapear la tabla direcciones_cliente de la base de datos. */
package com.erpmotos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "direcciones_cliente")
public class DireccionCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_direccion_PK;

    @ManyToOne
    @JoinColumn(name = "id_cliente_FK")
    private Cliente cliente;

    private String calle;
    private String numero_ext;
    private String numero_int;
    private String colonia;
    private String municipio;
    private String estado;
    private String cp;
    private String referencias;
    private boolean es_principal;

    public DireccionCliente() {}

    public int getId_direccion_PK() { return id_direccion_PK; }
    public void setId_direccion_PK(int id_direccion_PK) { this.id_direccion_PK = id_direccion_PK; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }

    public String getNumero_ext() { return numero_ext; }
    public void setNumero_ext(String numero_ext) { this.numero_ext = numero_ext; }

    public String getNumero_int() { return numero_int; }
    public void setNumero_int(String numero_int) { this.numero_int = numero_int; }

    public String getColonia() { return colonia; }
    public void setColonia(String colonia) { this.colonia = colonia; }

    public String getMunicipio() { return municipio; }
    public void setMunicipio(String municipio) { this.municipio = municipio; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getCp() { return cp; }
    public void setCp(String cp) { this.cp = cp; }

    public String getReferencias() { return referencias; }
    public void setReferencias(String referencias) { this.referencias = referencias; }

    public boolean isEs_principal() { return es_principal; }
    public void setEs_principal(boolean es_principal) { this.es_principal = es_principal; }
}
