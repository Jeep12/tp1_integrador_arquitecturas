package com.ak4n1.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "facturas_productos")
public class Factura_Producto implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "idFactura")
    private Factura factura;

    @Id
    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Producto producto;

    @Column(name = "cantidad")
    private int cantidad;

    public Factura_Producto() {
    }

    public Factura_Producto(Factura factura, Producto producto, int cantidad) {
        this.factura = factura;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Factura_Producto [" +
                "factura=" + factura +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura_Producto that = (Factura_Producto) o;
        return Objects.equals(factura, that.factura) &&
               Objects.equals(producto, that.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factura, producto);
    }
}
