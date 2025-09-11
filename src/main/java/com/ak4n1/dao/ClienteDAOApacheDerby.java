package com.ak4n1.dao;

import com.ak4n1.entity.Cliente;

import java.util.List;

public class ClienteDAOApacheDerby implements ClienteDAO {
    @Override
    public List<Cliente> getAllClientes() {
        return List.of();
    }

    @Override
    public void close() {

    }
}
