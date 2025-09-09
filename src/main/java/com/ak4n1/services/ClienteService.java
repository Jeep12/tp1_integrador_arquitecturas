package com.ak4n1.services;

import com.ak4n1.utils.ClienteFacturacion;

import java.util.List;

public interface ClienteService {

    void close();

    List<ClienteFacturacion> getClientesPorFacturacion1();


}
