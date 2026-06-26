/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Repositorio CRUD para la entidad Ingreso. */
package com.erpmotos.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.erpmotos.model.Ingreso;

@Repository
public interface IIngreso extends CrudRepository<Ingreso, Integer> {

    @Query("SELECT i FROM Ingreso i WHERE i.venta.id_venta_PK = :idVenta")
    List<Ingreso> findByVentaId(@Param("idVenta") int idVenta);
}
