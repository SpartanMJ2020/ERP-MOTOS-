package com.erpmotos.security;

import java.util.Map;
import java.util.Set;

public final class RolePermissions {

    private static final Map<String, Set<String>> MODULO_ROLES = Map.of(
        "ventas",     Set.of("Administrador", "Vendedor"),
        "clientes",   Set.of("Administrador", "Vendedor"),
        "finanzas",   Set.of("Administrador", "Contador"),
        "inventario", Set.of("Administrador", "Almacenista"),
        "empleados",  Set.of("Administrador")
    );

    private RolePermissions() {}

    public static boolean tieneAcceso(String uri, String metodo, String rol) {
        if ("Administrador".equals(rol)) return true;

        // Vendedor puede consultar inventario y empleados para registrar ventas
        if ("Vendedor".equals(rol) && "GET".equalsIgnoreCase(metodo)) {
            if (uri.contains("/inventario/") || uri.contains("/empleados/empleado/obtener")) {
                return true;
            }
        }

        // Almacenista puede consultar empleados para registrar movimientos
        if ("Almacenista".equals(rol) && "GET".equalsIgnoreCase(metodo)
                && uri.contains("/empleados/empleado/obtener")) {
            return true;
        }

        String modulo = detectarModulo(uri);
        if (modulo == null) return false;

        Set<String> rolesPermitidos = MODULO_ROLES.get(modulo);
        return rolesPermitidos != null && rolesPermitidos.contains(rol);
    }

    private static String detectarModulo(String uri) {
        if (uri.contains("/ventas/"))     return "ventas";
        if (uri.contains("/clientes/"))   return "clientes";
        if (uri.contains("/finanzas/"))   return "finanzas";
        if (uri.contains("/inventario/")) return "inventario";
        if (uri.contains("/empleados/"))  return "empleados";
        return null;
    }
}
