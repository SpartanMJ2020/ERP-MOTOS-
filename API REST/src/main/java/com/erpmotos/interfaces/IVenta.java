/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Repositorio CRUD para la entidad Venta. */
package com.erpmotos.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.erpmotos.model.Venta;

@Repository
public interface IVenta extends CrudRepository<Venta, Integer> {}
