/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Implementar la lógica de control de los métodos CRUD para DireccionCliente. */
package com.erpmotos.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erpmotos.interfaceService.IDireccionClienteService;
import com.erpmotos.interfaces.IDireccionCliente;
import com.erpmotos.model.DireccionCliente;

@Service
public class DireccionClienteService implements IDireccionClienteService {

    @Autowired
    private IDireccionCliente data;

    @Override
    public int save(DireccionCliente obj) {
        int res = 0;
        DireccionCliente guardado = data.save(obj);
        if (guardado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public int update(DireccionCliente obj) {
        int res = 0;
        DireccionCliente actualizado = data.save(obj);
        if (actualizado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public List<DireccionCliente> listar() {
        return (List<DireccionCliente>) data.findAll();
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
