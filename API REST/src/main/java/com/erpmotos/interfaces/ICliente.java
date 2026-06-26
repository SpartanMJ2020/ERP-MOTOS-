/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Repositorio CRUD para la entidad Cliente. */
package com.erpmotos.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.erpmotos.model.Cliente;

@Repository
public interface ICliente extends CrudRepository<Cliente, Integer> {
    Cliente findByEmail(String email);
}
