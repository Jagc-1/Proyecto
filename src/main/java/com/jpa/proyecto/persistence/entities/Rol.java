package com.jpa.proyecto.persistence.entities;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RolEnum nombre;

    //UNA FORMA DE CREAR TABLAS INTERMEDIAS SIN CREAR LA ENTIDAD.
    @ManyToMany
    @JoinTable(
        name = "rol_permisos",
        joinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "permiso_id", referencedColumnName = "id")
    )
    private Set<Permiso> permiso;

    
    public Rol() {
    }


    public Rol(RolEnum nombre, Set<Permiso> permiso) {
        this.nombre = nombre;
        this.permiso = permiso;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public RolEnum getNombre() {
        return nombre;
    }


    public void setNombre(RolEnum nombre) {
        this.nombre = nombre;
    }


    public Set<Permiso> getPermiso() {
        return permiso;
    }


    public void setPermiso(Set<Permiso> permiso) {
        this.permiso = permiso;
    }


    @Override
    public String toString() {
        return "Rol [id=" + id + ", nombre=" + nombre + ", permiso=" + permiso + "]";
    }
    
    
}
