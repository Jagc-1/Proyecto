package com.jpa.proyecto.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "detalles_pedidos")
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @NotNull(message = "{NotNull.detallepedido.pedido}")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    @NotNull(message = "{NotNull.detallepedido.producto}")
    private Producto producto;

    @Min(value = 1,message = "Debe tener minimo un producto!!!")
    private int cantidad;
    
    @Size(min = 6, max = 20, message = "El número de línea del producto debe tener entre 6 y 20 caracteres")
    private int numero_linea;

    @NotNull(message = "{NotNull.detallepedido.precio_unidad}")
    private int precio_unidad;

    public DetallePedido() {
    }
    public DetallePedido(Pedido pedido, Producto producto, int cantidad, int numero_linea, int precio_unidad) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.numero_linea = numero_linea;
        this.precio_unidad = precio_unidad;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
    public int getNumero_linea() {
        return numero_linea;
    }
    public void setNumero_linea(int numero_linea) {
        this.numero_linea = numero_linea;
    }
    public int getPrecio_unidad() {
        return precio_unidad;
    }
    public void setPrecio_unidad(int precio_unidad) {
        this.precio_unidad = precio_unidad;
    }
    @Override
    public String toString() {
        return "DetallePedido [id=" + id + ", pedido=" + pedido + ", producto=" + producto + ", cantidad=" + cantidad
                + ", numero_linea=" + numero_linea + ", precio_unidad=" + precio_unidad + "]";
    }

    
}
