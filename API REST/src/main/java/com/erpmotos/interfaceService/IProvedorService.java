/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Definir los métodos CRUD del servicio de Provedor. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.model.Provedor;

public interface IProvedorService {
    public int save(Provedor obj);
    public int update(Provedor obj);
    public List<Provedor> listar();
    public void eliminar(int id);
}
