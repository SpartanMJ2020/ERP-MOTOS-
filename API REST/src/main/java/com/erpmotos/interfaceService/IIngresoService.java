/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Definir los métodos CRUD del servicio de Ingreso. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.model.Ingreso;

public interface IIngresoService {
    public int save(Ingreso obj);
    public int update(Ingreso obj);
    public List<Ingreso> listar();
    public void eliminar(int id);
}
