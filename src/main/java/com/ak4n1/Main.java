package com.ak4n1;


import com.ak4n1.service.ClienteService;
import com.ak4n1.service.ClienteServiceImpl;
import com.ak4n1.service.ProductoService;
import com.ak4n1.service.ProductoServiceImpl;
import com.ak4n1.util.CSVDataLoaderMariaDB;


public class Main {
    public static void main(String[] args) {

        /*
         Cargar datos en mariadb desde los csv
        try {
            CSVDataLoaderMariaDB dataLoader = new CSVDataLoaderMariaDB();
            dataLoader.initialize();
            dataLoader.loadAllData();
            dataLoader.close();


        } catch (Exception e) {
            System.err.println("Error durante la carga de datos: " + e.getMessage());
            e.printStackTrace();
        }



         Cargar datos en apache derby desde los csv

        CSVDataLoaderDerby loader = new CSVDataLoaderDerby();
        try {
            loader.initialize();
            loader.loadAllData();
            System.out.println("datos cargados");
        } catch (Exception e) {
            System.err.println("error al cargar los datos");
            e.printStackTrace();
        } finally {
            loader.close();
        }
         */


        ProductoService ps = ProductoServiceImpl.getInstance();

        System.out.println(ps.getProductoQueMasRecaudo());


        ClienteService cs = ClienteServiceImpl.getInstance();

        System.out.println(cs.getClientesPorFacturacion1());


    }
}