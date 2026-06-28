/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Implementar la lógica de control de los métodos CRUD para Provedor. */
package com.erpmotos.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erpmotos.interfaceService.IProvedorService;
import com.erpmotos.interfaces.IProvedor;
import com.erpmotos.model.Provedor;

@Service
public class ProvedorService implements IProvedorService {

    @Autowired
    private IProvedor data;

    @Override
    public int save(Provedor obj) {
        int res = 0;
        Provedor guardado = data.save(obj);
        if (guardado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public int update(Provedor obj) {
        int res = 0;
        Provedor actualizado = data.save(obj);
        if (actualizado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public List<Provedor> listar() {
        return (List<Provedor>) data.findAll();
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
