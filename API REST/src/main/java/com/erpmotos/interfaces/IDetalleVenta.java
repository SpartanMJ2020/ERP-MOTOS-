/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Repositorio CRUD para la entidad DetalleVenta. */
package com.erpmotos.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.erpmotos.model.DetalleVenta;

@Repository
public interface IDetalleVenta extends CrudRepository<DetalleVenta, Integer> {

    @Query("SELECT d FROM DetalleVenta d WHERE d.venta.id_venta_PK = :idVenta")
    List<DetalleVenta> findByVentaId(@Param("idVenta") int idVenta);
}
