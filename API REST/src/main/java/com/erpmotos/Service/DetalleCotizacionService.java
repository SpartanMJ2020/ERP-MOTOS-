/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Implementar la lógica de control de los métodos CRUD para DetalleCotizacion. */
package com.erpmotos.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erpmotos.interfaceService.IDetalleCotizacionService;
import com.erpmotos.interfaces.IDetalleCotizacion;
import com.erpmotos.model.DetalleCotizacion;

@Service
public class DetalleCotizacionService implements IDetalleCotizacionService {

    @Autowired
    private IDetalleCotizacion data;

    @Override
    public int save(DetalleCotizacion obj) {
        int res = 0;
        DetalleCotizacion guardado = data.save(obj);
        if (guardado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public int update(DetalleCotizacion obj) {
        int res = 0;
        DetalleCotizacion actualizado = data.save(obj);
        if (actualizado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public List<DetalleCotizacion> listar() {
        return (List<DetalleCotizacion>) data.findAll();
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
