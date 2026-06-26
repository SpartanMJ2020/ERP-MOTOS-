/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Clientes
 * Objetivo: Definir los métodos CRUD y de búsqueda del servicio de Cliente. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.model.Cliente;

public interface IClienteService {
    public int save(Cliente c);
    public int update(Cliente c);
    public List<Cliente> listar();
    public void eliminar(int id);
    public Cliente buscarPorEmail(String email);
}
