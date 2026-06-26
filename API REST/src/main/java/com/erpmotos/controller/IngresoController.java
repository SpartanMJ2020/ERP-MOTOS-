/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Controlador REST para la entidad Ingreso con operaciones CRUD. */
package com.erpmotos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.erpmotos.interfaceService.IIngresoService;
import com.erpmotos.model.Ingreso;

@RestController
@RequestMapping("/api/finanzas/ingreso")
public class IngresoController {

    @Autowired
    private IIngresoService service;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody Ingreso obj) {
        try {
            int res = service.save(obj);
            if (res == 1) return ResponseEntity.ok("Registro de ingreso guardado con éxito.");
            return ResponseEntity.badRequest().body("Error al guardar el registro de ingreso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Ingreso obj) {
        try {
            int res = service.update(obj);
            if (res == 1) return ResponseEntity.ok("Registro de ingreso actualizado con éxito.");
            return ResponseEntity.badRequest().body("Error al actualizar el registro de ingreso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/obtener")
    public ResponseEntity<List<Ingreso>> obtener() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok("Registro de ingreso con ID " + id + " eliminado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar: " + e.getMessage());
        }
    }
}
