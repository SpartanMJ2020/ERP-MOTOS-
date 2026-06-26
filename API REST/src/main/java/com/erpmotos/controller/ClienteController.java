/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Controlador REST para la entidad Cliente con operaciones CRUD. */
package com.erpmotos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.erpmotos.interfaceService.IClienteService;
import com.erpmotos.model.Cliente;

@RestController
@RequestMapping("/api/clientes/cliente")
public class ClienteController {

    @Autowired
    private IClienteService service;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody Cliente obj) {
        try {
            int res = service.save(obj);
            if (res == 1) return ResponseEntity.ok("Registro de cliente guardado con éxito.");
            return ResponseEntity.badRequest().body("Error al guardar el registro de cliente.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Cliente obj) {
        try {
            int res = service.update(obj);
            if (res == 1) return ResponseEntity.ok("Registro de cliente actualizado con éxito.");
            return ResponseEntity.badRequest().body("Error al actualizar el registro de cliente.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/obtener")
    public ResponseEntity<List<Cliente>> obtener() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok("Registro de cliente con ID " + id + " eliminado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar: " + e.getMessage());
        }
    }

    @GetMapping("/buscar/{email}")
    public ResponseEntity<Cliente> buscarPorEmail(@PathVariable String email) {
        Cliente c = service.buscarPorEmail(email);
        if (c == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c);
    }
}
