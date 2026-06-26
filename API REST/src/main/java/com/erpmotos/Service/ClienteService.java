/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Módulo: Clientes
 * Objetivo: Implementar la lógica de control de los métodos CRUD y búsqueda para Cliente. */
package com.erpmotos.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erpmotos.interfaceService.IClienteService;
import com.erpmotos.interfaces.ICliente;
import com.erpmotos.model.Cliente;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ICliente data;

    @Override
    public int save(Cliente c) {
        int res = 0;
        Cliente guardado = data.save(c);
        if (guardado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public int update(Cliente c) {
        int res = 0;
        Cliente actualizado = data.save(c);
        if (actualizado != null) {
            res = 1;
        }
        return res;
    }

    @Override
    public List<Cliente> listar() {
        return (List<Cliente>) data.findAll();
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public Cliente buscarPorEmail(String email) {
        return data.findByEmail(email);
    }
}
