package com.ak4n1.services;

import com.ak4n1.entity.Producto;

public interface ProductoService {

    Producto getProductoQueMasRecaudo();

    void close();
}
