/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Repositorio CRUD para la entidad Empleado. */
package com.erpmotos.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.erpmotos.model.Empleado;

@Repository
public interface IEmpleado extends CrudRepository<Empleado, Integer> {
    Empleado findByEmail(String email);
}
