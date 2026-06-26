/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Implementar la lógica de control de los métodos CRUD para CreditoCliente. */
package com.erpmotos.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erpmotos.interfaceService.ICreditoClienteService;
import com.erpmotos.interfaces.ICreditoCliente;
import com.erpmotos.model.CreditoCliente;

@Service
public class CreditoClienteService implements ICreditoClienteService {

    @Autowired
    private ICreditoCliente data;

    @Override
    public int save(CreditoCliente obj) {
        int res = 0;
        CreditoCliente guardado = data.save(obj);
        if (guardado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public int update(CreditoCliente obj) {
        int res = 0;
        CreditoCliente actualizado = data.save(obj);
        if (actualizado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public List<CreditoCliente> listar() {
        return (List<CreditoCliente>) data.findAll();
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
