package com.ak4n1.service;

import com.ak4n1.util.ClienteFacturacion;

import java.util.List;

public interface ClienteService {

    void close();

    List<ClienteFacturacion> getClientesPorFacturacion();
    List<ClienteFacturacion> getClientesPorFacturacionJQPL();

}
