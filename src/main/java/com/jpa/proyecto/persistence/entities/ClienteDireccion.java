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
@Table(name = "cliente_direccion")
public class ClienteDireccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "contacto_id")
    @NotNull(message = "{NotNull.clientedireccion.contacto}")
    private Contacto contacto;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id")
    @NotNull(message = "{NotNull.clientedireccion.ubicacion}")
    private Ubicacion ubicacion;

    public ClienteDireccion() {
    }

    public ClienteDireccion(Contacto contacto, Ubicacion ubicacion) {
        this.contacto = contacto;
        this.ubicacion = ubicacion;
    }

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
        return "ClienteDireccion [id=" + id + ", contacto=" + contacto + ", ubicacion=" + ubicacion + "]";
    }
}
