package com.jpa.proyecto.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{NotNull.cliente.nombre}")
    private String nombre;

    @NotNull(message = "{NotNull.cliente.apellido}")
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "credito_id")
    @NotNull(message = "{NotNull.cliente.credito}")
    private Credito credito;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id")
    @NotNull(message = "{NotNull.cliente.ubicacion}")
    private Ubicacion ubicacion;

    // Constructor por defecto necesario para JPA
    public Cliente() {
    }

    // Constructor parametrizado para facilitar la creación de instancias
    public Cliente(String nombre, String apellido, Empleado empleado, Credito credito, Ubicacion ubicacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.empleado = empleado;
        this.credito = credito;
        this.ubicacion = ubicacion;
    }

    // Getters y Setters
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", empleado=" + empleado
                + ", credito=" + credito + ", ubicacion=" + ubicacion + "]";
    }
}
