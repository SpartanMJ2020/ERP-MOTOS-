/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Repositorio CRUD para la entidad DetalleCotizacion. */
package com.erpmotos.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.erpmotos.model.DetalleCotizacion;

@Repository
public interface IDetalleCotizacion extends CrudRepository<DetalleCotizacion, Integer> {}
