/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Controlador REST para la entidad Venta con operaciones CRUD. */
package com.erpmotos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.erpmotos.interfaceService.IVentaService;
import com.erpmotos.dto.VentaConDetalleDTO;
import com.erpmotos.model.Venta;

@RestController
@RequestMapping("/api/ventas/venta")
public class VentaController {

    @Autowired
    private IVentaService service;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody Venta obj) {
        try {
            int res = service.save(obj);
            if (res == 1) return ResponseEntity.ok("Registro de venta guardado con éxito.");
            return ResponseEntity.badRequest().body("Error al guardar el registro de venta.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody VentaConDetalleDTO dto) {
        try {
            int res = service.registrarConDetalle(dto);
            if (res == 1) return ResponseEntity.ok("Venta registrada con éxito. Stock actualizado.");
            return ResponseEntity.badRequest().body("Error al registrar la venta.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            String msg = e.getMessage() != null && e.getMessage().contains("folio")
                ? "El folio ya existe. Usa un folio diferente."
                : "Error de integridad al registrar la venta.";
            return ResponseEntity.badRequest().body(msg);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody Venta obj) {
        try {
            int res = service.update(obj);
            if (res == 1) return ResponseEntity.ok("Registro de venta actualizado con éxito.");
            return ResponseEntity.badRequest().body("Error al actualizar el registro de venta.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/obtener")
    public ResponseEntity<List<Venta>> obtener() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok("Registro de venta con ID " + id + " eliminado con éxito.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar: " + e.getMessage());
        }
    }
}
