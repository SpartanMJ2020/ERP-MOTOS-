/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Implementar la lógica de control de los métodos CRUD para Venta. */
package com.erpmotos.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.erpmotos.dto.DetalleVentaItemDTO;
import com.erpmotos.dto.VentaConDetalleDTO;
import com.erpmotos.interfaceService.IVentaService;
import com.erpmotos.interfaces.IAccesorio;
import com.erpmotos.interfaces.ICliente;
import com.erpmotos.interfaces.ICreditoCliente;
import com.erpmotos.interfaces.IDetalleVenta;
import com.erpmotos.interfaces.IEmpleado;
import com.erpmotos.interfaces.IIngreso;
import com.erpmotos.interfaces.IMoto;
import com.erpmotos.interfaces.IMovimientoInventario;
import com.erpmotos.interfaces.IPagoCredito;
import com.erpmotos.interfaces.IVenta;
import com.erpmotos.model.Accesorio;
import com.erpmotos.model.Cliente;
import com.erpmotos.model.CreditoCliente;
import com.erpmotos.model.DetalleVenta;
import com.erpmotos.model.Empleado;
import com.erpmotos.model.Moto;
import com.erpmotos.model.MovimientoInventario;
import com.erpmotos.model.PagoCredito;
import com.erpmotos.model.Ingreso;
import com.erpmotos.model.Venta;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVenta data;

    @Autowired
    private IDetalleVenta detalleData;

    private float normalizarDescuentoItem(float valor, float baseItem) {
        if (Float.isNaN(valor) || valor <= 0) return 0;
        if (valor <= 100) return valor;
        if (baseItem <= 0) return 0;
        return Math.min((valor / baseItem) * 100f, 100f);
    }


    @Autowired
    private IMoto motoData;

    @Autowired
    private IAccesorio accesorioData;

    @Autowired
    private IMovimientoInventario movimientoData;

    @Autowired
    private ICliente clienteData;

    @Autowired
    private IEmpleado empleadoData;

    @Autowired
    private IIngreso ingresoData;

    @Autowired
    private ICreditoCliente creditoData;

    @Autowired
    private IPagoCredito pagoCreditoData;

    @Override
    public int save(Venta obj) {
        int res = 0;
        Venta guardado = data.save(obj);
        if (guardado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public int update(Venta obj) {
        int res = 0;
        Venta actualizado = data.save(obj);
        if (actualizado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public List<Venta> listar() {
        return (List<Venta>) data.findAll();
    }

    @Override
    @Transactional
    public void eliminar(int id) {
        Venta venta = data.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Venta no encontrada."));

        Empleado empleado = venta.getEmpleado();
        List<DetalleVenta> detalles = detalleData.findByVentaId(id);

        for (DetalleVenta detalle : detalles) {
            if ("moto".equals(detalle.getTipo_producto())) {
                motoData.findById(detalle.getId_producto_ref()).ifPresent(moto -> {
                    moto.setStock(moto.getStock() + detalle.getCantidad());
                    motoData.save(moto);
                });
            } else {
                accesorioData.findById(detalle.getId_producto_ref()).ifPresent(acc -> {
                    acc.setStock(acc.getStock() + detalle.getCantidad());
                    accesorioData.save(acc);
                });
            }

            if (empleado != null) {
                MovimientoInventario mov = new MovimientoInventario();
                mov.setTipo_producto(detalle.getTipo_producto());
                mov.setId_producto_ref(detalle.getId_producto_ref());
                mov.setEmpleado(empleado);
                mov.setTipo_movimiento("entrada");
                mov.setCantidad(detalle.getCantidad());
                mov.setMotivo("Cancelación venta " + venta.getFolio());
                movimientoData.save(mov);
            }

            detalleData.delete(detalle);
        }

        for (Ingreso ingreso : ingresoData.findByVentaId(id)) {
            ingresoData.delete(ingreso);
        }

        for (CreditoCliente credito : creditoData.findByVentaId(id)) {
            for (PagoCredito pago : pagoCreditoData.findByCreditoId(credito.getId_credito_PK())) {
                pagoCreditoData.delete(pago);
            }
            creditoData.delete(credito);
        }

        data.delete(venta);
    }

    @Override
    @Transactional
    public int registrarConDetalle(VentaConDetalleDTO dto) {
        if (dto.getDetalles() == null || dto.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("La venta debe incluir al menos un producto.");
        }
        if (dto.getCliente() == null || dto.getCliente().getId_cliente_PK() <= 0) {
            throw new IllegalArgumentException("Debe seleccionar un cliente válido.");
        }
        if (dto.getEmpleado() == null || dto.getEmpleado().getId_empleado_PK() <= 0) {
            throw new IllegalArgumentException("Debe seleccionar un empleado válido.");
        }

        Cliente cliente = clienteData.findById(dto.getCliente().getId_cliente_PK())
            .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado."));
        Empleado empleado = empleadoData.findById(dto.getEmpleado().getId_empleado_PK())
            .orElseThrow(() -> new IllegalArgumentException("Empleado no encontrado."));

        Venta venta = new Venta();
        venta.setFolio(dto.getFolio());
        venta.setCliente(cliente);
        venta.setEmpleado(empleado);
        venta.setMetodo_pago(dto.getMetodo_pago());
        venta.setEstado(dto.getEstado() != null ? dto.getEstado() : "completada");
        venta.setObservaciones(dto.getObservaciones());

        float subtotalItems = 0;
        List<DetalleVenta> detalles = new ArrayList<>();

        for (DetalleVentaItemDTO item : dto.getDetalles()) {
            float precioUnitario;
            int stockDisponible;
            String nombreProducto;

            if ("moto".equals(item.getTipo_producto())) {
                Moto moto = motoData.findById(item.getId_producto_ref())
                    .orElseThrow(() -> new IllegalArgumentException(
                        "Moto no encontrada (ID: " + item.getId_producto_ref() + ")"));
                if (!moto.isActivo()) {
                    throw new IllegalArgumentException("La moto " + moto.getModelo() + " no está activa.");
                }
                precioUnitario = moto.getPrecio_venta();
                stockDisponible = moto.getStock();
                nombreProducto = moto.getModelo();
            } else if ("accesorio".equals(item.getTipo_producto())) {
                Accesorio acc = accesorioData.findById(item.getId_producto_ref())
                    .orElseThrow(() -> new IllegalArgumentException(
                        "Accesorio no encontrado (ID: " + item.getId_producto_ref() + ")"));
                if (!acc.isActivo()) {
                    throw new IllegalArgumentException("El accesorio " + acc.getNombre() + " no está activo.");
                }
                precioUnitario = acc.getPrecio_venta();
                stockDisponible = acc.getStock();
                nombreProducto = acc.getNombre();
            } else {
                throw new IllegalArgumentException("Tipo de producto inválido: " + item.getTipo_producto());
            }

            if (item.getCantidad() <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor a cero para " + nombreProducto);
            }
            if (item.getCantidad() > stockDisponible) {
                throw new IllegalArgumentException(
                    "Stock insuficiente para " + nombreProducto + ". Disponible: " + stockDisponible);
            }

            float descuentoItem = item.getDescuento_item();
            float baseItem = item.getCantidad() * precioUnitario;
            descuentoItem = normalizarDescuentoItem(descuentoItem, baseItem);
            if (descuentoItem < 0 || descuentoItem > 100) {
                throw new IllegalArgumentException("El descuento por item debe ser entre 0 y 100.");
            }
            float subtotalItem = baseItem * (1 - descuentoItem / 100f);

            DetalleVenta detalle = new DetalleVenta();
            detalle.setTipo_producto(item.getTipo_producto());
            detalle.setId_producto_ref(item.getId_producto_ref());
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecio_unitario(precioUnitario);
            detalle.setDescuento_item(descuentoItem);
            detalle.setSubtotal_item(subtotalItem);
            detalles.add(detalle);
            subtotalItems += subtotalItem;
        }

        float descuento = dto.getDescuento();
        if (descuento < 0 || descuento > 100) {
            throw new IllegalArgumentException("El descuento total debe ser entre 0 y 100.");
        }
        float base = subtotalItems * (1 - descuento / 100f);
        float iva = base * 0.16f;
        float total = base + iva;

        venta.setSubtotal(subtotalItems);
        venta.setDescuento(descuento);
        venta.setIva(iva);
        venta.setTotal(total);

        Venta guardada = data.save(venta);

        for (DetalleVenta detalle : detalles) {
            detalle.setVenta(guardada);
            detalleData.save(detalle);

            if ("moto".equals(detalle.getTipo_producto())) {
                Moto moto = motoData.findById(detalle.getId_producto_ref()).get();
                moto.setStock(moto.getStock() - detalle.getCantidad());
                motoData.save(moto);
            } else {
                Accesorio acc = accesorioData.findById(detalle.getId_producto_ref()).get();
                acc.setStock(acc.getStock() - detalle.getCantidad());
                accesorioData.save(acc);
            }

            MovimientoInventario mov = new MovimientoInventario();
            mov.setTipo_producto(detalle.getTipo_producto());
            mov.setId_producto_ref(detalle.getId_producto_ref());
            mov.setEmpleado(empleado);
            mov.setTipo_movimiento("salida");
            mov.setCantidad(detalle.getCantidad());
            mov.setMotivo("Venta " + guardada.getFolio());
            movimientoData.save(mov);
        }

        return 1;
    }
}
