/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Definir los métodos CRUD del servicio de Marca. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.model.Marca;

public interface IMarcaService {
    public int save(Marca obj);
    public int update(Marca obj);
    public List<Marca> listar();
    public void eliminar(int id);
}
