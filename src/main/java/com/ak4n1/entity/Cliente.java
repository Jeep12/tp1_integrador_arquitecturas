package com.ak4n1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    private int idCliente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "email")
    private String email;

  // mappedby apunta al atributo 'cliente' en Factura y que Cliente no es dueño de la relacion
    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas;

    public Cliente() {
    }

    public Cliente(String email, String nombre) {
        this.email = email;
        this.nombre = nombre;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    @Override
    public String toString() {
        return "Cliente [ " +
                "email = '" + email + '\'' +
                ", idCliente = " + idCliente +
                ", nombre = '" + nombre + '\'' +
                ']';
    }
}