/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Definir los métodos CRUD del servicio de CategoriaMoto. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.model.CategoriaMoto;

public interface ICategoriaMotoService {
    public int save(CategoriaMoto obj);
    public int update(CategoriaMoto obj);
    public List<CategoriaMoto> listar();
    public void eliminar(int id);
}
