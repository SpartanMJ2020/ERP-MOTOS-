/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Implementar la lógica de control de los métodos CRUD para CategoriaMoto. */
package com.erpmotos.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erpmotos.interfaceService.ICategoriaMotoService;
import com.erpmotos.interfaces.ICategoriaMoto;
import com.erpmotos.model.CategoriaMoto;

@Service
public class CategoriaMotoService implements ICategoriaMotoService {

    @Autowired
    private ICategoriaMoto data;

    @Override
    public int save(CategoriaMoto obj) {
        int res = 0;
        CategoriaMoto guardado = data.save(obj);
        if (guardado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public int update(CategoriaMoto obj) {
        int res = 0;
        CategoriaMoto actualizado = data.save(obj);
        if (actualizado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public List<CategoriaMoto> listar() {
        return (List<CategoriaMoto>) data.findAll();
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
