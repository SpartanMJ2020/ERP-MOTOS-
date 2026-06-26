/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Implementar la lógica de control de los métodos CRUD para DetalleVenta. */
package com.erpmotos.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erpmotos.interfaceService.IDetalleVentaService;
import com.erpmotos.interfaces.IDetalleVenta;
import com.erpmotos.model.DetalleVenta;

@Service
public class DetalleVentaService implements IDetalleVentaService {

    @Autowired
    private IDetalleVenta data;

    @Override
    public int save(DetalleVenta obj) {
        int res = 0;
        DetalleVenta guardado = data.save(obj);
        if (guardado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public int update(DetalleVenta obj) {
        int res = 0;
        DetalleVenta actualizado = data.save(obj);
        if (actualizado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public List<DetalleVenta> listar() {
        return (List<DetalleVenta>) data.findAll();
    }

    @Override
    public List<DetalleVenta> listarPorVenta(int idVenta) {
        return data.findByVentaId(idVenta);
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
