/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Implementar la lógica de control de los métodos CRUD para Cotizacion. */
package com.erpmotos.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erpmotos.interfaceService.ICotizacionService;
import com.erpmotos.interfaces.ICotizacion;
import com.erpmotos.model.Cotizacion;

@Service
public class CotizacionService implements ICotizacionService {

    @Autowired
    private ICotizacion data;

    @Override
    public int save(Cotizacion obj) {
        int res = 0;
        Cotizacion guardado = data.save(obj);
        if (guardado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public int update(Cotizacion obj) {
        int res = 0;
        Cotizacion actualizado = data.save(obj);
        if (actualizado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public List<Cotizacion> listar() {
        return (List<Cotizacion>) data.findAll();
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
