/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Implementar la lógica de control de los métodos CRUD para CuentaFinanciera. */
package com.erpmotos.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erpmotos.interfaceService.ICuentaFinancieraService;
import com.erpmotos.interfaces.ICuentaFinanciera;
import com.erpmotos.model.CuentaFinanciera;

@Service
public class CuentaFinancieraService implements ICuentaFinancieraService {

    @Autowired
    private ICuentaFinanciera data;

    @Override
    public int save(CuentaFinanciera obj) {
        int res = 0;
        CuentaFinanciera guardado = data.save(obj);
        if (guardado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public int update(CuentaFinanciera obj) {
        int res = 0;
        CuentaFinanciera actualizado = data.save(obj);
        if (actualizado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public List<CuentaFinanciera> listar() {
        return (List<CuentaFinanciera>) data.findAll();
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
