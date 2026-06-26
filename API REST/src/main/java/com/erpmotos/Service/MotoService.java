/* Universidad Politécnica de Francisco I. Madero
 * Proyecto: ERP de Venta de Motos
 * Objetivo: Implementar la lógica de control de los métodos CRUD para Moto. */
package com.erpmotos.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erpmotos.interfaceService.IMotoService;
import com.erpmotos.interfaces.ICategoriaMoto;
import com.erpmotos.interfaces.IMarca;
import com.erpmotos.interfaces.IMoto;
import com.erpmotos.model.Moto;

@Service
public class MotoService implements IMotoService {

    @Autowired
    private IMoto data;

    @Autowired
    private IMarca marcaData;

    @Autowired
    private ICategoriaMoto categoriaData;

    @Override
    public int save(Moto obj) {
        resolverRelaciones(obj);
        Moto guardado = data.save(obj);
        return guardado != null ? 1 : 0;
    }

    @Override
    public int update(Moto obj) {
        if (obj.getId_moto_PK() <= 0) {
            throw new IllegalArgumentException("ID de moto inválido para actualizar.");
        }
        resolverRelaciones(obj);
        Moto actualizado = data.save(obj);
        return actualizado != null ? 1 : 0;
    }

    private void resolverRelaciones(Moto obj) {
        if (obj.getMarca() == null || obj.getMarca().getId_marca_PK() <= 0) {
            throw new IllegalArgumentException("Debe seleccionar una marca válida.");
        }
        if (obj.getCategoria() == null || obj.getCategoria().getId_categoria_PK() <= 0) {
            throw new IllegalArgumentException("Debe seleccionar una categoría válida.");
        }
        obj.setMarca(marcaData.findById(obj.getMarca().getId_marca_PK())
            .orElseThrow(() -> new IllegalArgumentException("Marca no encontrada.")));
        obj.setCategoria(categoriaData.findById(obj.getCategoria().getId_categoria_PK())
            .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada.")));
    }

    @Override
    public List<Moto> listar() {
        return (List<Moto>) data.findAll();
    }

    @Override
    public void eliminar(int id) {
        data.deleteById(id);
    }
}
