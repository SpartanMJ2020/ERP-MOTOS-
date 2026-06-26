/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Implementar la lógica de control de los métodos CRUD para Accesorio. */
package com.erpmotos.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erpmotos.interfaceService.IAccesorioService;
import com.erpmotos.interfaces.IAccesorio;
import com.erpmotos.model.Accesorio;

@Service
public class AccesorioService implements IAccesorioService {

    @Autowired
    private IAccesorio data;

    @Override
    public int save(Accesorio obj) {
        int res = 0;
        Accesorio guardado = data.save(obj);
        if (guardado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public int update(Accesorio obj) {
        int res = 0;
        Accesorio actualizado = data.save(obj);
        if (actualizado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public List<Accesorio> listar() {
        return (List<Accesorio>) data.findAll();
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
