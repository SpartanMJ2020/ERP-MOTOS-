/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Definir los métodos CRUD del servicio de DetalleCotizacion. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.model.DetalleCotizacion;

public interface IDetalleCotizacionService {
    public int save(DetalleCotizacion obj);
    public int update(DetalleCotizacion obj);
    public List<DetalleCotizacion> listar();
    public void eliminar(int id);
}
