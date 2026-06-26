/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Definir los métodos CRUD del servicio de MovimientoInventario. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.model.MovimientoInventario;

public interface IMovimientoInventarioService {
    public int save(MovimientoInventario obj);
    public int update(MovimientoInventario obj);
    public List<MovimientoInventario> listar();
    public void eliminar(int id);
}
