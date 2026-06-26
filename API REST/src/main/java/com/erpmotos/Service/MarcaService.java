/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Implementar la lógica de control de los métodos CRUD para Marca. */
package com.erpmotos.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erpmotos.interfaceService.IMarcaService;
import com.erpmotos.interfaces.IMarca;
import com.erpmotos.model.Marca;

@Service
public class MarcaService implements IMarcaService {

    @Autowired
    private IMarca data;

    @Override
    public int save(Marca obj) {
        int res = 0;
        Marca guardado = data.save(obj);
        if (guardado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public int update(Marca obj) {
        int res = 0;
        Marca actualizado = data.save(obj);
        if (actualizado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public List<Marca> listar() {
        return (List<Marca>) data.findAll();
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
