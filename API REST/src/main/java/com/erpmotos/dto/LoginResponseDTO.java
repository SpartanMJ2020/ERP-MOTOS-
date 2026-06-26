package com.erpmotos.dto;

public class LoginResponseDTO {

    private String token;
    private int id_empleado;
    private String nombre;
    private String email;
    private int id_rol;
    private String rol;

    public LoginResponseDTO() {}

    public LoginResponseDTO(String token, int id_empleado, String nombre, String email, int id_rol, String rol) {
        this.token = token;
        this.id_empleado = id_empleado;
        this.nombre = nombre;
        this.email = email;
        this.id_rol = id_rol;
        this.rol = rol;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public int getId_empleado() { return id_empleado; }
    public void setId_empleado(int id_empleado) { this.id_empleado = id_empleado; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getId_rol() { return id_rol; }
    public void setId_rol(int id_rol) { this.id_rol = id_rol; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}
