/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Implementar la lógica de control de los métodos CRUD para Egreso. */
package com.erpmotos.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erpmotos.interfaceService.IEgresoService;
import com.erpmotos.interfaces.IEgreso;
import com.erpmotos.model.Egreso;

@Service
public class EgresoService implements IEgresoService {

    @Autowired
    private IEgreso data;

    @Override
    public int save(Egreso obj) {
        int res = 0;
        Egreso guardado = data.save(obj);
        if (guardado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public int update(Egreso obj) {
        int res = 0;
        Egreso actualizado = data.save(obj);
        if (actualizado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public List<Egreso> listar() {
        return (List<Egreso>) data.findAll();
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
