package com.ak4n1.dao;

import com.ak4n1.entity.Factura_Producto;
import com.ak4n1.entity.Producto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class FacturaProductoDAODerby implements FacturaProductoDAO {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public FacturaProductoDAODerby() {
        this.emf = Persistence.createEntityManagerFactory("TP1_Derby");
        this.em = emf.createEntityManager();
    }

    @Override
    public List<Factura_Producto> getAllFacturasProductos() {
        return em.createQuery("SELECT fp FROM Factura_Producto fp", Factura_Producto.class)
                .getResultList();
    }

    @Override
    public void close() {
        em.close();
        emf.close();
    }


    @Override
    public Producto getProductoQueMasRecaudoJQPL() {
        try {
            return em.createQuery(
                            "SELECT p FROM Producto p " +
                                    "JOIN Factura_Producto fp ON fp.producto = p " +
                                    "GROUP BY p.idProducto, p.nombre, p.valor " +
                                    "ORDER BY SUM(fp.cantidad * p.valor) DESC",
                            Producto.class
                    ).setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
/*
consulta sql nativa.

select  f.idCliente ,fp.cantidad, p.valor, p.nombre, sum(fp.cantidad * p.valor) as recaudacion  from facturas_productos fp
inner join facturas f
on f.idFactura =fp.idFactura
inner join productos p
on fp.idProducto  = p.idProducto group by idCliente order by recaudacion desc limit 1 ;
*/
