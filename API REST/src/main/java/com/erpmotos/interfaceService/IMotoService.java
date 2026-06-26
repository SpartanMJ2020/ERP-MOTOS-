/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Definir los métodos CRUD del servicio de Moto. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.model.Moto;

public interface IMotoService {
    public int save(Moto obj);
    public int update(Moto obj);
    public List<Moto> listar();
    public void eliminar(int id);
}
