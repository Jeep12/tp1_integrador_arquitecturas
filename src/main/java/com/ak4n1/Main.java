package com.ak4n1;

import com.ak4n1.service.ClienteService;
import com.ak4n1.service.ClienteServiceImpl;
import com.ak4n1.service.ProductoService;
import com.ak4n1.service.ProductoServiceImpl;
import com.ak4n1.util.CSVDataLoaderMariaDB;
import com.ak4n1.util.CargarDatos;

public class Main {
  public static void main(String[] args) {

    // CargarDatos.cargarApacheDerby();
    // CargarDatos.cargarMariaDB();

    ProductoService ps = ProductoServiceImpl.getInstance();
    ClienteService cs = ClienteServiceImpl.getInstance();

    System.out.println("Producto que más recaudó:");
    System.out.println(ps.getProductoQueMasRecaudoJPQL());
    System.out.println(" \n\n\n Clientes por facturación:");
    System.out.println(cs.getClientesPorFacturacionJQPL());

  }
}