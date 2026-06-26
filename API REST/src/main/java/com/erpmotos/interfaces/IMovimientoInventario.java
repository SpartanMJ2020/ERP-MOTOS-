/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Repositorio CRUD para la entidad MovimientoInventario. */
package com.erpmotos.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.erpmotos.model.MovimientoInventario;

@Repository
public interface IMovimientoInventario extends CrudRepository<MovimientoInventario, Integer> {}
