package com.jpa.proyecto.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;

@Entity
@Table(name = "dimensiones")
public class Dimension {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DecimalMin(value = "0.0", message = "El alto debe ser mayor o igual a 0")
    private double alto;

    @DecimalMin(value = "0.0", message = "El ancho debe ser mayor o igual a 0")
    private double ancho;

    @DecimalMin(value = "0.0", message = "El largo debe ser mayor o igual a 0")
    private double largo;

    @DecimalMin(value = "0.0", message = "El peso debe ser mayor o igual a 0")
    private double peso;

    public Dimension() {
    }

    public Dimension(double alto, double ancho, double largo, double peso) {
        this.alto = alto;
        this.ancho = ancho;
        this.largo = largo;
        this.peso = peso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Dimension [id=" + id + ", alto=" + alto + ", ancho=" + ancho + ", largo=" + largo + ", peso=" + peso
                + "]";
    }

    

}
