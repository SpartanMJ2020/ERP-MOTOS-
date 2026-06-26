/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Definir los métodos CRUD del servicio de Accesorio. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.model.Accesorio;

public interface IAccesorioService {
    public int save(Accesorio obj);
    public int update(Accesorio obj);
    public List<Accesorio> listar();
    public void eliminar(int id);
}
