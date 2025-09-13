package com.ak4n1.dao;

import com.ak4n1.entity.Factura_Producto;
import com.ak4n1.entity.Producto;
import java.util.List;

public interface FacturaProductoDAO {
    List<Factura_Producto> getAllFacturasProductos();
    Producto getProductoQueMasRecaudoJQPL();
    void close();
}
