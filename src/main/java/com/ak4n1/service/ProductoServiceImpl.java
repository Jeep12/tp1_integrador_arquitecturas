package com.ak4n1.service;

import com.ak4n1.dao.FacturaProductoDAO;
import com.ak4n1.dao.FacturaProductoDAOMariaDB;
import com.ak4n1.entity.Factura_Producto;
import com.ak4n1.entity.Producto;
import com.ak4n1.factory.DAOFactory;
import com.ak4n1.util.TipoBaseDeDatos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoServiceImpl implements ProductoService {

    private final FacturaProductoDAO facturaProductoDAO;

    private static ProductoService instance;

    private final static TipoBaseDeDatos tipoBD = TipoBaseDeDatos.MARIADB;

    private ProductoServiceImpl() {
        DAOFactory factory = DAOFactory.getFactory(tipoBD);
        this.facturaProductoDAO = factory.createFacturaProductoDAO();
    }

    public static ProductoService getInstance() {
        if (instance == null) {
            instance = new ProductoServiceImpl();
        }
        return instance;
    }

    @Override
    public Producto getProductoQueMasRecaudo() {
        List<Factura_Producto> ventas = facturaProductoDAO.getAllFacturasProductos();
        if (ventas.isEmpty()) {
            return null;
        }

        //clave producto, valor double
        Map<Producto, Double> recaudacionMap = new HashMap<>();

        // Recorremos todos los registros de facturas_productos
        for (Factura_Producto fp : ventas) {

            // Tomamos el producto asociado a la venta
            Producto p = fp.getProducto();

            // Calculamos el total de esta venta (
            double total = fp.getCantidad() * p.getValor();

            // obtenemos el total acumulado hasta ahora para este producto (p)
            Double totalActual = recaudacionMap.get(p);

            // si no hay ningun total previo (producto aunn no registrado) le asignamos 0.
            if (totalActual == null) {
                totalActual = 0.0;
            }

            // volvemos a sumar el total de esta venta al acumulado
            double nuevoTotal = totalActual + total;

            // guardamos el nuevo total actualizado
            recaudacionMap.put(p, nuevoTotal);
        }

        // Recorremos el keyset del map para encontrar el producto con mas recaudacion
        Producto productoTop = null;
        double maxRecaudacion = 0;
        for (Producto p : recaudacionMap.keySet()) {
            double total = recaudacionMap.get(p);
            if (total > maxRecaudacion) {
                maxRecaudacion = total;
                productoTop = p;
            }
        }

        this.close();

        return productoTop;
    }

    @Override
    public Producto getProductoQueMasRecaudoJPQL() {
        return facturaProductoDAO.getProductoQueMasRecaudoJQPL();
    }

    @Override
    public void close() {
        facturaProductoDAO.close();
    }
}