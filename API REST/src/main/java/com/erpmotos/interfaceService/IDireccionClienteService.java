/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Definir los métodos CRUD del servicio de DireccionCliente. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.model.DireccionCliente;

public interface IDireccionClienteService {
    public int save(DireccionCliente obj);
    public int update(DireccionCliente obj);
    public List<DireccionCliente> listar();
    public void eliminar(int id);
}
