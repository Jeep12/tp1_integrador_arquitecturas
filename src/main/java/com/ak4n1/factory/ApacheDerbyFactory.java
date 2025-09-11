package com.ak4n1.factory;

import com.ak4n1.dao.ClienteDAO;
import com.ak4n1.dao.ClienteDAODerby;
import com.ak4n1.dao.FacturaProductoDAO;
import com.ak4n1.dao.FacturaProductoDAODerby;

public class ApacheDerbyFactory extends DAOFactory {

    @Override
    public ClienteDAO createClienteDAO() {
        return new ClienteDAODerby();
    }

    @Override
    public FacturaProductoDAO createFacturaProductoDAO() {
        return new FacturaProductoDAODerby();
    }
}
