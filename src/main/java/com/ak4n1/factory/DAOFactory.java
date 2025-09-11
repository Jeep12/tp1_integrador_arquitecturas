package com.ak4n1.factory;

import com.ak4n1.dao.ClienteDAO;
import com.ak4n1.dao.FacturaProductoDAO;
import com.ak4n1.util.TipoBaseDeDatos;

public abstract class DAOFactory {

    public abstract ClienteDAO createClienteDAO();
    public abstract FacturaProductoDAO createFacturaProductoDAO();

    public static DAOFactory getFactory(TipoBaseDeDatos type) {
        switch (type) {
            case MARIADB:
                return new MariaDBFactory();
            case DERBY:
                return new ApacheDerbyFactory();
            default:
                throw new IllegalArgumentException("Tipo de BD no soportado: " + type);
        }
    }
}
