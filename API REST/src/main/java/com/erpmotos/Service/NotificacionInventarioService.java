package com.erpmotos.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.erpmotos.dto.AlertaStockResponse;
import com.erpmotos.dto.StockBajoDTO;
import com.erpmotos.interfaceService.INotificacionInventarioService;
import com.erpmotos.interfaces.IAccesorio;
import com.erpmotos.interfaces.IMoto;
import com.erpmotos.model.Accesorio;
import com.erpmotos.model.Moto;

@Service
public class NotificacionInventarioService implements INotificacionInventarioService {

    public static final int UMBRAL_STOCK_BAJO = 8;

    @Autowired
    private IMoto motoData;

    @Autowired
    private IAccesorio accesorioData;

    @Override
    public AlertaStockResponse listarStockBajo() {
        List<StockBajoDTO> productos = new ArrayList<>();

        for (Moto moto : motoData.findByStockLessThanAndActivoTrue(UMBRAL_STOCK_BAJO)) {
            String nombre = (moto.getMarca() != null ? moto.getMarca().getNombre() + " " : "")
                + moto.getModelo();
            productos.add(new StockBajoDTO(
                "moto", moto.getId_moto_PK(), nombre.trim(), moto.getStock()));
        }

        for (Accesorio acc : accesorioData.findByStockLessThanAndActivoTrue(UMBRAL_STOCK_BAJO)) {
            productos.add(new StockBajoDTO(
                "accesorio", acc.getId_accesorio_PK(), acc.getNombre(), acc.getStock()));
        }

        productos.sort(Comparator.comparingInt(StockBajoDTO::getStock));

        return new AlertaStockResponse(UMBRAL_STOCK_BAJO, productos.size(), productos);
    }
}
