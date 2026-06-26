/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Repositorio CRUD para la entidad PagoCredito. */
package com.erpmotos.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.erpmotos.model.PagoCredito;

@Repository
public interface IPagoCredito extends CrudRepository<PagoCredito, Integer> {

    @Query("SELECT p FROM PagoCredito p WHERE p.credito.id_credito_PK = :idCredito")
    List<PagoCredito> findByCreditoId(@Param("idCredito") int idCredito);
}
