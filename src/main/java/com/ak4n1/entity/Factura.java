package com.ak4n1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    private int idFactura;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;


    public Factura() {
    }

    public Factura(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    @Override
    public String toString() {
        return "Factura{" +
                "idFactura=" + idFactura +
                ", cliente=" + cliente +
                '}';
    }
}