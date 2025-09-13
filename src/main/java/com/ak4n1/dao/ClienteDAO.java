package com.ak4n1.dao;

import com.ak4n1.entity.Cliente;
import com.ak4n1.util.ClienteFacturacion;
import java.util.List;

public interface ClienteDAO {
    List<Cliente> getAllClientes();
    List<ClienteFacturacion> getClientesPorFacturacionJQPL();
    void close();
}