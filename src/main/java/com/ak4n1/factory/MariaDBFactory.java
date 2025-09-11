package com.ak4n1.factory;

import com.ak4n1.dao.ClienteDAO;
import com.ak4n1.dao.ClienteDAOMariaDB;
import com.ak4n1.dao.FacturaProductoDAO;
import com.ak4n1.dao.FacturaProductoDAOMariaDB;

public class MariaDBFactory extends  DAOFactory {

    @Override
    public ClienteDAO createClienteDAO() {
        return new ClienteDAOMariaDB();
    }

    @Override
    public FacturaProductoDAO createFacturaProductoDAO() {
        return new FacturaProductoDAOMariaDB();
    }
}
