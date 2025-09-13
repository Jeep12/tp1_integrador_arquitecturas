package com.ak4n1.dao;

import com.ak4n1.entity.Cliente;
import com.ak4n1.util.ClienteFacturacion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ClienteDAOMariaDB implements ClienteDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public ClienteDAOMariaDB() {
        this.emf = Persistence.createEntityManagerFactory("TP1_MariaDB");
        this.em = emf.createEntityManager();
    }

    @Override
    public List<Cliente> getAllClientes() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList();
    }

    @Override
    public List<ClienteFacturacion> getClientesPorFacturacionJQPL() {
        return em.createQuery(
            "SELECT NEW com.ak4n1.util.ClienteFacturacion(c, SUM(fp.cantidad * p.valor)) " +
            "FROM Cliente c " +
            "JOIN c.facturas f " +
            "JOIN Factura_Producto fp ON fp.factura = f " +
            "JOIN fp.producto p " +
            "GROUP BY c.idCliente, c.nombre, c.email " +
            "ORDER BY SUM(fp.cantidad * p.valor) DESC", 
            ClienteFacturacion.class
        ).getResultList();
    }

    @Override
    public void close() {
        em.close();
        emf.close();
    }
}
/*
Consulta sql nativa.
        SELECT
        c.idCliente,
        c.nombre,
        c.email,
        SUM(fp.cantidad * p.valor) AS total_facturado
    FROM clientes c
    JOIN facturas f ON f.idCliente = c.idCliente
    JOIN facturas_productos fp ON fp.idFactura = f.idFactura
    JOIN productos p ON p.idProducto = fp.idProducto
    GROUP BY c.idCliente
    ORDER BY total_facturado DESC;
 */