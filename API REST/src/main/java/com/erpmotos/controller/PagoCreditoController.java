/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Controlador REST para la entidad PagoCredito con operaciones CRUD. */
package com.erpmotos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.erpmotos.interfaceService.IPagoCreditoService;
import com.erpmotos.model.PagoCredito;

@RestController
@RequestMapping("/api/finanzas/pago-credito")
public class PagoCreditoController {

    @Autowired
    private IPagoCreditoService service;

    @PostMapping("/guardar")
    public ResponseEntity<String> guardar(@RequestBody PagoCredito obj) {
        try {
            int res = service.save(obj);
            if (res == 1) return ResponseEntity.ok("Registro de pago de crédito guardado con éxito.");
            return ResponseEntity.badRequest().body("Error al guardar el registro de pago de crédito.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @PutMapping("/actualizar")
    public ResponseEntity<String> actualizar(@RequestBody PagoCredito obj) {
        try {
            int res = service.update(obj);
            if (res == 1) return ResponseEntity.ok("Registro de pago de crédito actualizado con éxito.");
            return ResponseEntity.badRequest().body("Error al actualizar el registro de pago de crédito.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    @GetMapping("/obtener")
    public ResponseEntity<List<PagoCredito>> obtener() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok("Registro de pago de crédito con ID " + id + " eliminado con éxito.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar: " + e.getMessage());
        }
    }
}
