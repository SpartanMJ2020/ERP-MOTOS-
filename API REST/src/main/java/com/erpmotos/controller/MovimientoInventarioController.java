/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Controlador REST para la entidad MovimientoInventario con operaciones CRUD. */
package com.erpmotos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.erpmotos.interfaceService.IMovimientoInventarioService;
import com.erpmotos.model.MovimientoInventario;

@RestController
@RequestMapping("/api/inventario/movimiento")
public class MovimientoInventarioController {

    @Autowired
    private IMovimientoInventarioService service;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody MovimientoInventario obj) {
        try {
            int res = service.save(obj);
            if (res == 1) return ResponseEntity.ok("Movimiento registrado y stock actualizado con éxito.");
            return ResponseEntity.badRequest().body("Error al guardar el registro de movimiento.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody MovimientoInventario obj) {
        try {
            int res = service.update(obj);
            if (res == 1) return ResponseEntity.ok("Registro de movimiento actualizado con éxito.");
            return ResponseEntity.badRequest().body("Error al actualizar el registro de movimiento.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/obtener")
    public ResponseEntity<List<MovimientoInventario>> obtener() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok("Registro de movimiento con ID " + id + " eliminado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar: " + e.getMessage());
        }
    }
}
