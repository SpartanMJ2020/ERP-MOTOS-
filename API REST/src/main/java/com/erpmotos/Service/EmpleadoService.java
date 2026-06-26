/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Empleados
 * Objetivo: Implementar la lógica de control de los métodos CRUD y autenticación para Empleado. */
package com.erpmotos.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erpmotos.interfaceService.IEmpleadoService;
import com.erpmotos.interfaces.IEmpleado;
import com.erpmotos.model.Empleado;

@Service
public class EmpleadoService implements IEmpleadoService {

    @Autowired
    private IEmpleado data;

    @Override
    public int save(Empleado e) {
        int res = 0;
        Empleado guardado = data.save(e);
        if (guardado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public int update(Empleado e) {
        int res = 0;
        Empleado actualizado = data.save(e);
        if (actualizado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public List<Empleado> listar() {
        return (List<Empleado>) data.findAll();
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public Empleado buscarPorEmail(String email) {
        return data.findByEmail(email);
    }
}
