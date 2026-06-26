/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Implementar la lógica de control de los métodos CRUD para RolEmpleado. */
package com.erpmotos.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erpmotos.interfaceService.IRolEmpleadoService;
import com.erpmotos.interfaces.IRolEmpleado;
import com.erpmotos.model.RolEmpleado;

@Service
public class RolEmpleadoService implements IRolEmpleadoService {

    @Autowired
    private IRolEmpleado data;

    @Override
    public int save(RolEmpleado obj) {
        int res = 0;
        RolEmpleado guardado = data.save(obj);
        if (guardado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public int update(RolEmpleado obj) {
        int res = 0;
        RolEmpleado actualizado = data.save(obj);
        if (actualizado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public List<RolEmpleado> listar() {
        return (List<RolEmpleado>) data.findAll();
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
