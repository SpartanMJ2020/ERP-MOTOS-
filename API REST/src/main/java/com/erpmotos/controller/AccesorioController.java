/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Controlador REST para la entidad Accesorio con operaciones CRUD. */
package com.erpmotos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.erpmotos.interfaceService.IAccesorioService;
import com.erpmotos.model.Accesorio;

@RestController
@RequestMapping("/api/inventario/accesorio")
public class AccesorioController {

    @Autowired
    private IAccesorioService service;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody Accesorio obj) {
        try {
            int res = service.save(obj);
            if (res == 1) return ResponseEntity.ok("Registro de accesorio guardado con éxito.");
            return ResponseEntity.badRequest().body("Error al guardar el registro de accesorio.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Accesorio obj) {
        try {
            int res = service.update(obj);
            if (res == 1) return ResponseEntity.ok("Registro de accesorio actualizado con éxito.");
            return ResponseEntity.badRequest().body("Error al actualizar el registro de accesorio.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/obtener")
    public ResponseEntity<List<Accesorio>> obtener() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok("Registro de accesorio con ID " + id + " eliminado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar: " + e.getMessage());
        }
    }
}
