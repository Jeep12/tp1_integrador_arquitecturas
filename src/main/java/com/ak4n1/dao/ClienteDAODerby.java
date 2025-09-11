package com.ak4n1.dao;

import com.ak4n1.entity.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ClienteDAODerby implements ClienteDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public ClienteDAODerby() {
        this.emf = Persistence.createEntityManagerFactory("TP1_Derby");
        this.em = emf.createEntityManager();
    }

    @Override
    public List<Cliente> getAllClientes() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class)
                .getResultList();
    }

    @Override
    public void close() {
        em.close();
        emf.close();
    }
}
/*
posible consulta sql.
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
