package com.jpa.proyecto.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El stock máximo no puede ser nulo")
    @Min(value = 0, message = "El stock máximo debe ser mayor o igual a cero")
    private int stock_maximo;

    @NotNull(message = "El stock mínimo no puede ser nulo")
    @Min(value = 0, message = "El stock mínimo debe ser mayor o igual a cero")
    private int stock_minimo;

    @NotNull(message = "El stock actual no puede ser nulo")
    @Min(value = 0, message = "El stock actual debe ser mayor o igual a cero")
    private int stock_actual;

    public Stock() {
    }

    public Stock(int stock_maximo, int stock_minimo, int stock_actual) {
        this.stock_maximo = stock_maximo;
        this.stock_minimo = stock_minimo;
        this.stock_actual = stock_actual;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStock_maximo() {
        return stock_maximo;
    }

    public void setStock_maximo(int stock_maximo) {
        this.stock_maximo = stock_maximo;
    }

    public int getStock_minimo() {
        return stock_minimo;
    }

    public void setStock_minimo(int stock_minimo) {
        this.stock_minimo = stock_minimo;
    }

    public int getStock_actual() {
        return stock_actual;
    }

    public void setStock_actual(int stock_actual) {
        this.stock_actual = stock_actual;
    }

    @Override
    public String toString() {
        return "Stock [id=" + id + ", stock_maximo=" + stock_maximo + ", stock_minimo=" + stock_minimo
                + ", stock_actual=" + stock_actual + "]";
    }

    
    
}
