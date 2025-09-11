package com.ak4n1.util;

import com.ak4n1.entity.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVDataLoaderDerby {
    
    private static final String PERSISTENCE_UNIT_NAME = "TP1_Derby";
    private EntityManagerFactory emf;
    private EntityManager em;
    
    private Map<Integer, Cliente> clientesMap = new HashMap<>();
    private Map<Integer, Producto> productosMap = new HashMap<>();
    private Map<Integer, Factura> facturasMap = new HashMap<>();
    
    public static void main(String[] args) {
        CSVDataLoaderDerby loader = new CSVDataLoaderDerby();
        try {
            loader.initialize();
            loader.loadAllData();
            System.out.println("Datos cargados exitosamente en Apache Derby!");
        } catch (Exception e) {
            System.err.println("Error al cargar datos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            loader.close();
        }
    }
    
    public void initialize() {
        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = emf.createEntityManager();
    }
    
    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
    
    public void loadAllData() throws IOException {
        em.getTransaction().begin();
        
        try {
            // Cargar datos en orden de dependencias
            loadClientes();
            loadProductos();
            loadFacturas();
            loadFacturasProductos();
            
            em.getTransaction().commit();
            System.out.println("Transacción completada exitosamente en Derby");
            
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
    
    private void loadClientes() throws IOException {
        System.out.println("Cargando clientes en Derby...");
        
        try (FileReader reader = new FileReader("src/main/resources/csv/clientes.csv");
             CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
            
            for (CSVRecord record : parser) {
                int idCliente = Integer.parseInt(record.get("idCliente"));
                String nombre = record.get("nombre");
                String email = record.get("email");
                
                Cliente cliente = new Cliente(email, nombre);
                cliente.setIdCliente(idCliente); // Establecer ID manualmente para mantener referencias
                
                em.persist(cliente);
                clientesMap.put(idCliente, cliente);
            }
        }
        
        System.out.println("Clientes cargados en Derby: " + clientesMap.size());
    }
    
    private void loadProductos() throws IOException {
        System.out.println("Cargando productos en Derby...");
        
        try (FileReader reader = new FileReader("src/main/resources/csv/productos.csv");
             CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
            
            for (CSVRecord record : parser) {
                int idProducto = Integer.parseInt(record.get("idProducto"));
                String nombre = record.get("nombre");
                float valor = Float.parseFloat(record.get("valor"));
                
                Producto producto = new Producto(nombre, valor);
                producto.setIdProducto(idProducto); // Establecer ID manualmente para mantener referencias
                
                em.persist(producto);
                productosMap.put(idProducto, producto);
            }
        }
        
        System.out.println("Productos cargados en Derby: " + productosMap.size());
    }
    
    private void loadFacturas() throws IOException {
        System.out.println("Cargando facturas en Derby...");
        
        try (FileReader reader = new FileReader("src/main/resources/csv/facturas.csv");
             CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
            
            for (CSVRecord record : parser) {
                int idFactura = Integer.parseInt(record.get("idFactura"));
                int idCliente = Integer.parseInt(record.get("idCliente"));
                
                Cliente cliente = clientesMap.get(idCliente);
                if (cliente == null) {
                    System.err.println("Cliente no encontrado para factura " + idFactura + ": " + idCliente);
                    continue;
                }
                
                Factura factura = new Factura(cliente);
                factura.setIdFactura(idFactura); // Establecer ID manualmente para mantener referencias
                
                em.persist(factura);
                facturasMap.put(idFactura, factura);
            }
        }
        
        System.out.println("Facturas cargadas en Derby: " + facturasMap.size());
    }
    
    private void loadFacturasProductos() throws IOException {
        System.out.println("Cargando facturas-productos en Derby...");
        
        try (FileReader reader = new FileReader("src/main/resources/csv/facturas-productos.csv");
             CSVParser parser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
            
            int count = 0;
            for (CSVRecord record : parser) {
                int idFactura = Integer.parseInt(record.get("idFactura"));
                int idProducto = Integer.parseInt(record.get("idProducto"));
                int cantidad = Integer.parseInt(record.get("cantidad"));
                
                Factura factura = facturasMap.get(idFactura);
                Producto producto = productosMap.get(idProducto);
                
                if (factura == null) {
                    System.err.println("Factura no encontrada: " + idFactura);
                    continue;
                }
                
                if (producto == null) {
                    System.err.println("Producto no encontrado: " + idProducto);
                    continue;
                }
                
                Factura_Producto facturaProducto = new Factura_Producto(factura, producto, cantidad);
                em.persist(facturaProducto);
                count++;
            }
            
            System.out.println("Facturas-productos cargados en Derby: " + count);
        }
    }
}
