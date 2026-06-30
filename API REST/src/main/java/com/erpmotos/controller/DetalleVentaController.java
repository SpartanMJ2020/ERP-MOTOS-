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
        List<DetalleVenta> detalles = service.listarPorVenta(idVenta);
        detalles.forEach(d -> {
            float baseItem = d.getPrecio_unitario() * d.getCantidad();
            float porcentaje = d.getDescuento_item();
            if (porcentaje > 100 && baseItem > 0) {
                porcentaje = Math.min((porcentaje / baseItem) * 100f, 100f);
                d.setDescuento_item(porcentaje);
            }
            if (baseItem > 0) {
                d.setSubtotal_item(baseItem * (1 - porcentaje / 100f));
            }
        });
        return ResponseEntity.ok(detalles);
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
