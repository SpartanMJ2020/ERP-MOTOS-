/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Definir los métodos CRUD del servicio de PagoCredito. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.model.PagoCredito;

public interface IPagoCreditoService {
    public int save(PagoCredito obj);
    public int update(PagoCredito obj);
    public List<PagoCredito> listar();
    public void eliminar(int id);
}
