/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Repositorio CRUD para la entidad DireccionCliente. */
package com.erpmotos.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.erpmotos.model.DireccionCliente;

@Repository
public interface IDireccionCliente extends CrudRepository<DireccionCliente, Integer> {}
