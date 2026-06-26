package com.erpmotos.security;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(1)
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            chain.doFilter(request, response);
            return;
        }

        String uri = request.getRequestURI();
        if (uri.endsWith("/empleados/empleado/login") || uri.endsWith("/empleados/empleado/logout")) {
            chain.doFilter(request, response);
            return;
        }

        if (!uri.startsWith("/api/")) {
            chain.doFilter(request, response);
            return;
        }

        String auth = request.getHeader("Authorization");
        String token = (auth != null && auth.startsWith("Bearer ")) ? auth.substring(7) : null;
        SessionData sesion = authService.validarToken(token);

        if (sesion == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write("No autorizado. Inicia sesión para continuar.");
            return;
        }

        if (!RolePermissions.tieneAcceso(uri, request.getMethod(), sesion.getRol())) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write("No tienes permiso para acceder a este recurso.");
            return;
        }

        chain.doFilter(request, response);
    }
}
