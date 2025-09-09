package com.ak4n1.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @Column(name = "idProducto")
    private int idProducto;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "valor")
    private float valor;

    @OneToMany(mappedBy = "producto")
    private List<Factura_Producto> facturaProductos;

    public Producto() {
    }

    public Producto(String nombre, float valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public List<Factura_Producto> getFacturaProductos() {
        return facturaProductos;
    }

    public void setFacturaProductos(List<Factura_Producto> facturaProductos) {
        this.facturaProductos = facturaProductos;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                '}';
    }
}
