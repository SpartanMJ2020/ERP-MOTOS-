/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Definir los métodos CRUD del servicio de Venta. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.dto.VentaConDetalleDTO;
import com.erpmotos.model.Venta;

public interface IVentaService {
    public int save(Venta obj);
    public int update(Venta obj);
    public List<Venta> listar();
    public void eliminar(int id);
    public int registrarConDetalle(VentaConDetalleDTO dto);
}
