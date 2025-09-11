package com.ak4n1.dao;

import com.ak4n1.entity.Factura_Producto;

import java.util.List;

public class FacturaProductoDAOApacheDerby implements FacturaProductoDAO {
    @Override
    public List<Factura_Producto> getAllFacturasProductos() {
        return List.of();
    }

    @Override
    public void close() {

    }
}
