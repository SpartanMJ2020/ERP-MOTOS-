/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Repositorio CRUD para la entidad CreditoCliente. */
package com.erpmotos.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.erpmotos.model.CreditoCliente;

@Repository
public interface ICreditoCliente extends CrudRepository<CreditoCliente, Integer> {

    @Query("SELECT c FROM CreditoCliente c WHERE c.venta.id_venta_PK = :idVenta")
    List<CreditoCliente> findByVentaId(@Param("idVenta") int idVenta);
}
