package com.jpa.proyecto.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ubicaciones")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ciudad_id")
    @NotNull(message = "{NotNull.ubicacion.ciudad}")
    private Ciudad ciudad;

    @NotEmpty(message = "{NotEmpty.ubicacion.direccion1}")
    private String direccion1;

    private String direccion2;

    @Size(min = 1, max = 6, message = "El codigo postal debe tener uno o más caracteres y menos de 6")
    private String codigoPostal;

    // Constructor por defecto necesario para JPA
    public Ubicacion() {
    }

    // Constructor parametrizado para facilitar la creación de instancias
    public Ubicacion(Ciudad ciudad, String direccion1, String direccion2, String codigoPostal) {
        this.ciudad = ciudad;
        this.direccion1 = direccion1;
        this.direccion2 = direccion2;
        this.codigoPostal = codigoPostal;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion1() {
        return direccion1;
    }

    public void setDireccion1(String direccion1) {
        this.direccion1 = direccion1;
    }

    public String getDireccion2() {
        return direccion2;
    }

    public void setDireccion2(String direccion2) {
        this.direccion2 = direccion2;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    // Método toString para una representación en cadena útil para depuración
    @Override
    public String toString() {
        return "Ubicacion [id=" + id + ", ciudad=" + ciudad + ", direccion1=" + direccion1 + ", direccion2="
                + direccion2 + ", codigoPostal=" + codigoPostal + "]";
    }
}
