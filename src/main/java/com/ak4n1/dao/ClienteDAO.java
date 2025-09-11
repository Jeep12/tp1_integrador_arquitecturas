package com.ak4n1.dao;

import com.ak4n1.entity.Cliente;
import java.util.List;

public interface ClienteDAO {
    List<Cliente> getAllClientes();
    void close();
}