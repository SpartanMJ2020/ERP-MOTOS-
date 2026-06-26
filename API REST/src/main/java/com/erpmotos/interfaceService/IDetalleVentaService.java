/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Definir los métodos CRUD del servicio de DetalleVenta. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.model.DetalleVenta;

public interface IDetalleVentaService {
    public int save(DetalleVenta obj);
    public int update(DetalleVenta obj);
    public List<DetalleVenta> listar();
    public List<DetalleVenta> listarPorVenta(int idVenta);
    public void eliminar(int id);
}
