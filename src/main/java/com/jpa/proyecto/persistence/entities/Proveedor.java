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
@Table(name = "proveedores")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{NotNull.proveedor.nombres}")
    private String nombres;

    @NotNull(message = "{NotNull.proveedor.apellidos}")
    private String apellidos;

    @NotNull(message = "{NotNull.proveedor.nombre_empresa}")
    private String nombre_empresa;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;

    public Proveedor() {
    }

    public Proveedor(String nombres, String apellidos, String nombre_empresa, Ubicacion ubicacion) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nombre_empresa = nombre_empresa;
        this.ubicacion = ubicacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Proveedor [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", nombre_empresa="
                + nombre_empresa + ", ubicacion=" + ubicacion + "]";
    }

    
    
}
