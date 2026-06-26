/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Implementar la lógica de control de los métodos CRUD para MovimientoInventario. */
package com.erpmotos.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.erpmotos.interfaceService.IMovimientoInventarioService;
import com.erpmotos.interfaces.IAccesorio;
import com.erpmotos.interfaces.IEmpleado;
import com.erpmotos.interfaces.IMoto;
import com.erpmotos.interfaces.IMovimientoInventario;
import com.erpmotos.model.Accesorio;
import com.erpmotos.model.Empleado;
import com.erpmotos.model.Moto;
import com.erpmotos.model.MovimientoInventario;

@Service
public class MovimientoInventarioService implements IMovimientoInventarioService {

    @Autowired
    private IMovimientoInventario data;

    @Autowired
    private IMoto motoData;

    @Autowired
    private IAccesorio accesorioData;

    @Autowired
    private IEmpleado empleadoData;

    @Override
    @Transactional
    public int save(MovimientoInventario obj) {
        if (obj.getEmpleado() == null || obj.getEmpleado().getId_empleado_PK() <= 0) {
            throw new IllegalArgumentException("Debe seleccionar un empleado válido.");
        }
        if (obj.getId_producto_ref() <= 0) {
            throw new IllegalArgumentException("Debe seleccionar un producto válido.");
        }
        if (obj.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }

        Empleado empleado = empleadoData.findById(obj.getEmpleado().getId_empleado_PK())
            .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado."));
        obj.setEmpleado(empleado);

        actualizarStock(obj);

        MovimientoInventario guardado = data.save(obj);
        return guardado != null ? 1 : 0;
    }

    private void actualizarStock(MovimientoInventario obj) {
        String tipo = obj.getTipo_movimiento();

        if ("moto".equals(obj.getTipo_producto())) {
            Moto moto = motoData.findById(obj.getId_producto_ref())
                .orElseThrow(() -> new IllegalArgumentException("Moto no encontrada."));
            moto.setStock(calcularNuevoStock(moto.getStock(), obj.getCantidad(), tipo));
            motoData.save(moto);
        } else if ("accesorio".equals(obj.getTipo_producto())) {
            Accesorio acc = accesorioData.findById(obj.getId_producto_ref())
                .orElseThrow(() -> new IllegalArgumentException("Accesorio no encontrado."));
            acc.setStock(calcularNuevoStock(acc.getStock(), obj.getCantidad(), tipo));
            accesorioData.save(acc);
        } else {
            throw new IllegalArgumentException("Tipo de producto inválido.");
        }
    }

    private int calcularNuevoStock(int stockActual, int cantidad, String tipoMovimiento) {
        return switch (tipoMovimiento) {
            case "entrada" -> stockActual + cantidad;
            case "salida" -> {
                if (cantidad > stockActual) {
                    throw new IllegalArgumentException(
                        "Stock insuficiente. Disponible: " + stockActual);
                }
                yield stockActual - cantidad;
            }
            case "ajuste" -> cantidad;
            default -> throw new IllegalArgumentException("Tipo de movimiento inválido.");
        };
    }

    @Override
    public int update(MovimientoInventario obj) {
        int res = 0;
        MovimientoInventario actualizado = data.save(obj);
        if (actualizado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public List<MovimientoInventario> listar() {
        return (List<MovimientoInventario>) data.findAll();
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
