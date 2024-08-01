package com.jpa.proyecto.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "contactos")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 10, max = 10, message = "El numero de telefono debe ser igual a 10 digitos")
    private String telefono;

    @Size(min = 10, max = 10, message = "El codigo fax debe ser igual a 10 digitos")
    private String fax;

    @Size(min = 3, max = 6, message = "La extension es un numero de tres a seis digitos")
    private String extension;
    
    @Email(message = "{Email.contacto.email}")
    private String email;

    public Contacto() {
    }
    
    public Contacto(String telefono, String fax, String extension, String email) {
        this.telefono = telefono;
        this.fax = fax;
        this.extension = extension;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Contacto [id=" + id + ", telefono=" + telefono + ", fax=" + fax + ", extension=" + extension
                + ", email=" + email + "]";
    }
}
