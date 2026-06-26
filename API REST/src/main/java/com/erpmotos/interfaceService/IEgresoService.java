/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Definir los métodos CRUD del servicio de Egreso. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.model.Egreso;

public interface IEgresoService {
    public int save(Egreso obj);
    public int update(Egreso obj);
    public List<Egreso> listar();
    public void eliminar(int id);
}
