package com.erpmotos.security;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final Map<String, SessionData> sesiones = new ConcurrentHashMap<>();

    public String crearSesion(int idEmpleado, String rol) {
        String token = UUID.randomUUID().toString();
        sesiones.put(token, new SessionData(idEmpleado, rol));
        return token;
    }

    public SessionData validarToken(String token) {
        if (token == null || token.isBlank()) return null;
        return sesiones.get(token);
    }

    public void cerrarSesion(String token) {
        if (token != null) sesiones.remove(token);
    }
}
