/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Controlador REST para la entidad DetalleVenta con operaciones CRUD. */
package com.erpmotos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.erpmotos.interfaceService.IDetalleVentaService;
import com.erpmotos.model.DetalleVenta;

@RestController
@RequestMapping("/api/ventas/detalle")
public class DetalleVentaController {

    @Autowired
    private IDetalleVentaService service;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody DetalleVenta obj) {
        try {
            int res = service.save(obj);
            if (res == 1) return ResponseEntity.ok("Registro de detalle de venta guardado con éxito.");
            return ResponseEntity.badRequest().body("Error al guardar el registro de detalle de venta.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody DetalleVenta obj) {
        try {
            int res = service.update(obj);
            if (res == 1) return ResponseEntity.ok("Registro de detalle de venta actualizado con éxito.");
            return ResponseEntity.badRequest().body("Error al actualizar el registro de detalle de venta.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/obtener")
    public ResponseEntity<List<DetalleVenta>> obtener() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/obtener/{idVenta}")
    public ResponseEntity<List<DetalleVenta>> obtenerPorVenta(@PathVariable int idVenta) {
        return ResponseEntity.ok(service.listarPorVenta(idVenta));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok("Registro de detalle de venta con ID " + id + " eliminado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar: " + e.getMessage());
        }
    }
}
