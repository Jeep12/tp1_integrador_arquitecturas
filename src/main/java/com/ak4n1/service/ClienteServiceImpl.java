package com.ak4n1.service;

import com.ak4n1.dao.ClienteDAO;
import com.ak4n1.dao.FacturaProductoDAO;
import com.ak4n1.entity.Factura_Producto;
import com.ak4n1.factory.DAOFactory;
import com.ak4n1.util.ClienteFacturacion;
import com.ak4n1.util.TipoBaseDeDatos;

import java.util.*;

public class ClienteServiceImpl implements ClienteService {

    private static ClienteService instance;

    private final FacturaProductoDAO facturaProductoDAO;
    private final ClienteDAO clienteDAO;

    private final static TipoBaseDeDatos tipoBD = TipoBaseDeDatos.DERBY;


    private ClienteServiceImpl() {
        DAOFactory factory = DAOFactory.getFactory(tipoBD);
        this.facturaProductoDAO = factory.createFacturaProductoDAO();
        this.clienteDAO = factory.createClienteDAO();
    }

    public static ClienteService getInstance() {
        if (instance == null) {
            instance = new ClienteServiceImpl();
        }
        return instance;
    }


    @Override
    public List<ClienteFacturacion> getClientesPorFacturacion() {



        List<Factura_Producto> ventas = facturaProductoDAO.getAllFacturasProductos();
        if (ventas.isEmpty()) return Collections.emptyList();

        //La clave del map es el id del cliente
        Map<Integer, ClienteFacturacion> clienteMap = new HashMap<>();

        //recorremos todos los registros de facturas_productos
        for (Factura_Producto fp : ventas) {
            //guardamos el id del cliente (esto es posible porque tenemos una relacion bidireccional)
            Integer idCliente = fp.getFactura().getCliente().getIdCliente();
            //guardamos el total de la venta
            double totalVenta = fp.getCantidad() * fp.getProducto().getValor();

            //obtenemos la facturacion del cliente y si es null la guardamos sino acumulamos
            ClienteFacturacion cf = clienteMap.get(idCliente);
            if (cf == null) {
                clienteMap.put(idCliente, new ClienteFacturacion(fp.getFactura().getCliente(), totalVenta));
            } else {
                cf.addTotal(totalVenta);
            }
        }

        //convertimos el map en una lista para poder ordenarla
        List<ClienteFacturacion> listaClientes = new ArrayList<>(clienteMap.values());
        listaClientes.sort((a, b) -> Double.compare(b.getTotal(), a.getTotal()));

        this.close();
        return listaClientes;
    }



    @Override
    public List<ClienteFacturacion> getClientesPorFacturacionJQPL() {
        return clienteDAO.getClientesPorFacturacionJQPL();
    }

    @Override
    public void close() {
        facturaProductoDAO.close();
        clienteDAO.close();
    }
}
