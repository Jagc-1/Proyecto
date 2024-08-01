package com.jpa.proyecto.persistence.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @NotNull(message = "La fecha del pedido no puede ser nula")
    @PastOrPresent(message = "La fecha del pedido debe ser en el pasado o en el presente")
    private LocalDateTime fechaPedido;

    @NotNull(message = "La fecha esperada no puede ser nula")
    @FutureOrPresent(message = "La fecha esperada debe ser en el futuro o en el presente")
    private LocalDateTime fechaEsperada;

    private LocalDateTime fechaEntregada;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    @NotNull(message = "{NotNull.pedido.estado}")
    private Estado estado;

    @Size(min = 1,max = 250,message = "El mensaje tiene un limite de 250 letras!!!")
    private String comentarios;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull(message = "{NotNull.pedido.cliente}")
    private Cliente cliente;

    @NotNull(message = "{NotNull.pedido.metodo_pago}")
    private String metodo_pago;

    public Pedido() {
    }

    public Pedido(
            @NotNull(message = "La fecha del pedido no puede ser nula") @PastOrPresent(message = "La fecha del pedido debe ser en el pasado o en el presente") LocalDateTime fechaPedido,
            @NotNull(message = "La fecha esperada no puede ser nula") @FutureOrPresent(message = "La fecha esperada debe ser en el futuro o en el presente") LocalDateTime fechaEsperada,
            LocalDateTime fechaEntregada, Estado estado, String comentarios, Cliente cliente, String metodo_pago) {
        this.fechaPedido = fechaPedido;
        this.fechaEsperada = fechaEsperada;
        this.fechaEntregada = fechaEntregada;
        this.estado = estado;
        this.comentarios = comentarios;
        this.cliente = cliente;
        this.metodo_pago = metodo_pago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public LocalDateTime getFechaEsperada() {
        return fechaEsperada;
    }

    public void setFechaEsperada(LocalDateTime fechaEsperada) {
        this.fechaEsperada = fechaEsperada;
    }

    public LocalDateTime getFechaEntregada() {
        return fechaEntregada;
    }

    public void setFechaEntregada(LocalDateTime fechaEntregada) {
        this.fechaEntregada = fechaEntregada;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    @Override
    public String toString() {
        return "Pedido [id=" + id + ", fechaPedido=" + fechaPedido + ", fechaEsperada=" + fechaEsperada
                + ", fechaEntregada=" + fechaEntregada + ", estado=" + estado + ", comentarios=" + comentarios
                + ", cliente=" + cliente + ", metodo_pago=" + metodo_pago + "]";
    }

    
    
}
