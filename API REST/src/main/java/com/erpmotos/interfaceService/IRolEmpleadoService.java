/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Definir los métodos CRUD del servicio de RolEmpleado. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.model.RolEmpleado;

public interface IRolEmpleadoService {
    public int save(RolEmpleado obj);
    public int update(RolEmpleado obj);
    public List<RolEmpleado> listar();
    public void eliminar(int id);
}
