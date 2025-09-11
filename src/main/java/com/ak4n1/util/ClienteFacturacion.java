package com.ak4n1.util;

import com.ak4n1.entity.Cliente;

public class ClienteFacturacion {
    private Cliente cliente;
    private double total;

    public ClienteFacturacion(Cliente cliente, double total) {
        this.cliente = cliente;
        this.total = total;
    }

    public void addTotal(double cantidad) {
        this.total += cantidad;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "\n" +
                cliente +
                ", total=" + total;

    }
}