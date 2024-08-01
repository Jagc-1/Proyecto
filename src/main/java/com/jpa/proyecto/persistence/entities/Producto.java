package com.jpa.proyecto.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{NotNull.producto.nombre}")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "gamaProducto_id")
    @NotNull(message = "{NotNull.producto.gamaProducto}")
    private GamaProducto gamaProducto;

    @ManyToOne
    @JoinColumn(name = "dimension_id")
    @NotNull(message = "{NotNull.producto.dimension}")
    private Dimension dimension;

    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    @NotNull(message = "{NotNull.producto.proveedor}")
    private Proveedor proveedor;

    @Size(min = 1,max = 250,message = "Haz una descripcion con menos de 250 letras!!!")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "stock_id")
    @NotNull(message = "{NotNull.producto.stock}")
    private Stock stock;

    private double precio;

    public Producto() {
    }

    public Producto(@NotNull(message = "{NotNull.producto.nombre}") String nombre, GamaProducto gamaProducto,
            Dimension dimension, Proveedor proveedor, String descripcion, Stock stock,double precio) {
        this.nombre = nombre;
        this.gamaProducto = gamaProducto;
        this.dimension = dimension;
        this.proveedor = proveedor;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public GamaProducto getGamaProducto() {
        return gamaProducto;
    }

    public void setGamaProducto(GamaProducto gamaProducto) {
        this.gamaProducto = gamaProducto;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + ", gamaProducto=" + gamaProducto + ", dimension="
                + dimension + ", proveedor=" + proveedor + ", descripcion=" + descripcion + ", stock=" + stock
                + ", precio=" + precio + "]";
    }

    
    
}
