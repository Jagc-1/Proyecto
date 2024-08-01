package com.jpa.proyecto.persistence.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;

@Entity
@Table(name = "pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @FutureOrPresent(message = "La fecha y hora de pago debe ser en el presente o en el futuro")
    private LocalDateTime fecha_pago;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "El monto del pago debe ser positivo")
    private double monto;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Pago() {
    }

    public Pago(LocalDateTime fecha_pago, double monto, Pedido pedido) {
        this.fecha_pago = fecha_pago;
        this.monto = monto;
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(LocalDateTime fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "Pago [id=" + id + ", fecha_pago=" + fecha_pago + ", monto=" + monto + ", pedido=" + pedido + "]";
    }

    
}
