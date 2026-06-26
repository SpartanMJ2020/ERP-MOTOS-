/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Controlador REST para la entidad Moto con operaciones CRUD. */
package com.erpmotos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.erpmotos.interfaceService.IMotoService;
import com.erpmotos.model.Moto;

@RestController
@RequestMapping("/api/inventario/moto")
public class MotoController {

    @Autowired
    private IMotoService service;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody Moto obj) {
        try {
            int res = service.save(obj);
            if (res == 1) return ResponseEntity.ok("Registro de moto guardado con éxito.");
            return ResponseEntity.badRequest().body("Error al guardar el registro de moto.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Moto obj) {
        try {
            int res = service.update(obj);
            if (res == 1) return ResponseEntity.ok("Registro de moto actualizado con éxito.");
            return ResponseEntity.badRequest().body("Error al actualizar el registro de moto.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/obtener")
    public ResponseEntity<List<Moto>> obtener() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok("Registro de moto con ID " + id + " eliminado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar: " + e.getMessage());
        }
    }
}
