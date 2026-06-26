/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Definir los métodos CRUD del servicio de Cotizacion. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.model.Cotizacion;

public interface ICotizacionService {
    public int save(Cotizacion obj);
    public int update(Cotizacion obj);
    public List<Cotizacion> listar();
    public void eliminar(int id);
}
