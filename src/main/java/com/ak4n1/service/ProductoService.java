package com.ak4n1.service;

import com.ak4n1.entity.Producto;

public interface ProductoService {

    Producto getProductoQueMasRecaudo();
    Producto getProductoQueMasRecaudoJPQL();

    void close();
}
