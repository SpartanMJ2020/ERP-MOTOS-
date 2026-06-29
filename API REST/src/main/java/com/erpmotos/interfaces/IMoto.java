/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Repositorio CRUD para la entidad Moto. */
package com.erpmotos.interfaces;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.erpmotos.model.Moto;

@Repository
public interface IMoto extends CrudRepository<Moto, Integer> {
    List<Moto> findByStockLessThanAndActivoTrue(int stock);
}
