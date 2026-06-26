package com.erpmotos.security;

public class SessionData {

    private final int idEmpleado;
    private final String rol;

    public SessionData(int idEmpleado, String rol) {
        this.idEmpleado = idEmpleado;
        this.rol = rol;
    }

    public int getIdEmpleado() { return idEmpleado; }
    public String getRol() { return rol; }
}
