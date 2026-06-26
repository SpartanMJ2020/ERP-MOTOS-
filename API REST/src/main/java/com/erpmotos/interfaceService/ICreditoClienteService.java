/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Definir los métodos CRUD del servicio de CreditoCliente. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.model.CreditoCliente;

public interface ICreditoClienteService {
    public int save(CreditoCliente obj);
    public int update(CreditoCliente obj);
    public List<CreditoCliente> listar();
    public void eliminar(int id);
}
