/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Definir los métodos CRUD del servicio de CuentaFinanciera. */
package com.erpmotos.interfaceService;

import java.util.List;
import com.erpmotos.model.CuentaFinanciera;

public interface ICuentaFinancieraService {
    public int save(CuentaFinanciera obj);
    public int update(CuentaFinanciera obj);
    public List<CuentaFinanciera> listar();
    public void eliminar(int id);
}
