/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Repositorio CRUD para la entidad Accesorio. */
package com.erpmotos.interfaces;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.erpmotos.model.Accesorio;

@Repository
public interface IAccesorio extends CrudRepository<Accesorio, Integer> {
    List<Accesorio> findByStockLessThanAndActivoTrue(int stock);
}
