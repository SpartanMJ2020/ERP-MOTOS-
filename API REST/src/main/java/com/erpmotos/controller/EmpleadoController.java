/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Empleados
 * Objetivo: Controlador REST para la entidad Empleado con operaciones CRUD y login al ERP. */
package com.erpmotos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.erpmotos.dto.LoginResponseDTO;
import com.erpmotos.interfaceService.IEmpleadoService;
import com.erpmotos.model.Empleado;
import com.erpmotos.security.AuthService;

@RestController
@RequestMapping("/api/empleados/empleado")
public class EmpleadoController {

    @Autowired
    private IEmpleadoService service;

    @Autowired
    private AuthService authService;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody Empleado e) {
        try {
            int res = service.save(e);
            if (res == 1) return ResponseEntity.ok("Empleado guardado con éxito.");
            return ResponseEntity.badRequest().body("Error al guardar el empleado.");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error interno: " + ex.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Empleado e) {
        try {
            int res = service.update(e);
            if (res == 1) return ResponseEntity.ok("Empleado actualizado con éxito.");
            return ResponseEntity.badRequest().body("Error al actualizar el empleado.");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error interno: " + ex.getMessage());
        }
    }

    @GetMapping("/obtener")
    public ResponseEntity<List<Empleado>> obtener() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok("Empleado con ID " + id + " eliminado con éxito.");
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error al eliminar: " + ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Empleado e) {
        try {
            Empleado encontrado = service.buscarPorEmail(e.getEmail());
            if (encontrado == null)
                return ResponseEntity.status(401).body("Error: el email no está registrado.");
            if (!encontrado.isActivo())
                return ResponseEntity.status(403).body("Error: el empleado se encuentra inactivo.");
            if (!encontrado.getPassword_hash().equals(e.getPassword_hash()))
                return ResponseEntity.status(401).body("Error: contraseña incorrecta.");

            String rol = encontrado.getRol() != null ? encontrado.getRol().getNombre() : "Vendedor";
            int idRol = encontrado.getRol() != null ? encontrado.getRol().getId_rol_PK() : 2;
            String token = authService.crearSesion(encontrado.getId_empleado_PK(), rol);

            LoginResponseDTO resp = new LoginResponseDTO(
                token,
                encontrado.getId_empleado_PK(),
                encontrado.getNombre(),
                encontrado.getEmail(),
                idRol,
                rol
            );
            return ResponseEntity.ok(resp);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Error interno: " + ex.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(value = "Authorization", required = false) String auth) {
        if (auth != null && auth.startsWith("Bearer ")) {
            authService.cerrarSesion(auth.substring(7));
        }
        return ResponseEntity.ok("Sesión cerrada.");
    }

    @GetMapping("/buscar/{email}")
    public ResponseEntity<Empleado> buscarPorEmail(@PathVariable String email) {
        Empleado emp = service.buscarPorEmail(email);
        if (emp == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(emp);
    }
}
