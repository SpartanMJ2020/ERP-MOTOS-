/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Implementar la lógica de control de los métodos CRUD para PagoCredito. */
package com.erpmotos.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erpmotos.interfaceService.IPagoCreditoService;
import com.erpmotos.interfaces.IPagoCredito;
import com.erpmotos.model.PagoCredito;

@Service
public class PagoCreditoService implements IPagoCreditoService {

    @Autowired
    private IPagoCredito data;

    @Override
    public int save(PagoCredito obj) {
        int res = 0;
        PagoCredito guardado = data.save(obj);
        if (guardado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public int update(PagoCredito obj) {
        int res = 0;
        PagoCredito actualizado = data.save(obj);
        if (actualizado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public List<PagoCredito> listar() {
        return (List<PagoCredito>) data.findAll();
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
