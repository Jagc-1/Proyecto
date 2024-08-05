package com.jpa.proyecto.persistence.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
    
@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    private String nombres;
    private String apellidos;

    @ManyToOne
    @JoinColumn(name = "oficina_id")
    @NotNull(message = "{NotNull.empleado.oficina}")
    private Oficina oficina;

    @ManyToOne
    @JoinColumn(name = "jefe_id")
    @NotNull(message = "{NotNull.empleado.jefe}")
    private Empleado jefe;

    @NotNull(message = "{NotNull.empleado.puesto}")
    private String puesto;

    @Email(message = "{Email.empleado.email}")
    private String email;

    @ManyToOne
    @JoinColumn(name = "contacto_id")

    @NotNull(message = "{NotNull.empleado.contacto}")
    private Contacto contacto;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private Cuenta cuenta;

    public Empleado() {
    }

    public Empleado(String nombres, String apellidos, @NotNull(message = "{NotNull.empleado.oficina}") Oficina oficina,
            @NotNull(message = "{NotNull.empleado.jefe}") Empleado jefe,
            @NotNull(message = "{NotNull.empleado.puesto}") String puesto,
            @Email(message = "{Email.empleado.email}") String email,
            @NotNull(message = "{NotNull.empleado.contacto}") Contacto contacto, Cuenta cuenta) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.oficina = oficina;
        this.jefe = jefe;
        this.puesto = puesto;
        this.email = email;
        this.contacto = contacto;
        this.cuenta = cuenta;
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

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public Empleado getJefe() {
        return jefe;
    }

    public void setJefe(Empleado jefe) {
        this.jefe = jefe;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return "Empleado [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", oficina=" + oficina
                + ", jefe=" + jefe + ", puesto=" + puesto + ", email=" + email + ", contacto=" + contacto + ", cuenta="
                + cuenta + "]";
    }

    
}
