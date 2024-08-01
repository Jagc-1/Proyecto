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
@Table(name = "oficinas")
public class Oficina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contacto_id")
    @NotNull(message = "{NotNull.oficina.contacto}")
    private Contacto contacto;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id")
    @NotNull(message = "{NotNull.oficina.ubicacion}")
    private Ubicacion ubicacion;

    // Constructor por defecto necesario para JPA
    public Oficina() {
    }

    // Constructor parametrizado para facilitar la creación de instancias
    public Oficina(Contacto contacto, Ubicacion ubicacion) {
        this.contacto = contacto;
        this.ubicacion = ubicacion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Oficina [id=" + id + ", contacto=" + contacto + ", ubicacion=" + ubicacion + "]";
    }
}
