/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Implementar la lógica de control de los métodos CRUD para Ingreso. */
package com.erpmotos.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erpmotos.interfaceService.IIngresoService;
import com.erpmotos.interfaces.IIngreso;
import com.erpmotos.model.Ingreso;

@Service
public class IngresoService implements IIngresoService {

    @Autowired
    private IIngreso data;

    @Override
    public int save(Ingreso obj) {
        int res = 0;
        Ingreso guardado = data.save(obj);
        if (guardado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public int update(Ingreso obj) {
        int res = 0;
        Ingreso actualizado = data.save(obj);
        if (actualizado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public List<Ingreso> listar() {
        return (List<Ingreso>) data.findAll();
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
