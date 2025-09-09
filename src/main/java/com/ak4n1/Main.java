package com.ak4n1;


import com.ak4n1.services.ClienteServiceImpl;
import com.ak4n1.services.ProductoServiceImpl;

public class Main {
    public static void main(String[] args) {

        /*
        try {
            CSVDataLoader dataLoader = new CSVDataLoader();
            dataLoader.initialize();
            dataLoader.loadAllData();
            dataLoader.close();


        } catch (Exception e) {
            System.err.println("Error durante la carga de datos: " + e.getMessage());
            e.printStackTrace();
        }

         */

        ProductoServiceImpl ps = new ProductoServiceImpl();

        System.out.println(ps.getProductoQueMasRecaudo());


        ClienteServiceImpl cs = new ClienteServiceImpl();
        System.out.println(cs.getClientesPorFacturacion1());



    }
}