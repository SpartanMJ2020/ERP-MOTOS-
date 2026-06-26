/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Empleados
 * Objetivo: Definir los métodos CRUD y de autenticación del servicio de Empleado. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.model.Empleado;

public interface IEmpleadoService {
    public int save(Empleado e);
    public int update(Empleado e);
    public List<Empleado> listar();
    public void eliminar(int id);
    public Empleado buscarPorEmail(String email);
}
