package com.erpmotos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.erpmotos.dto.AlertaStockResponse;
import com.erpmotos.interfaceService.INotificacionInventarioService;

@RestController
@RequestMapping("/api/inventario/notificacion")
public class NotificacionInventarioController {

    @Autowired
    private INotificacionInventarioService service;

    @GetMapping("/stock-bajo")
    public ResponseEntity<AlertaStockResponse> stockBajo() {
        return ResponseEntity.ok(service.listarStockBajo());
    }
}
