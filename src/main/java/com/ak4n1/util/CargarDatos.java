package com.ak4n1.util;

public class CargarDatos {


    public static void cargarMariaDB() {
        try {
            CSVDataLoaderMariaDB dataLoader = new CSVDataLoaderMariaDB();
            dataLoader.initialize();
            dataLoader.loadAllData();
            dataLoader.close();


        } catch (Exception e) {
            System.err.println("error al cargar los datos");
            e.printStackTrace();
        }
    }

    public static void cargarApacheDerby() {
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
    }


}
